package alexey.githubsearchapp.android.presenters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import alexey.githubsearchapp.android.R;
import alexey.githubsearchapp.android.adapters.RepositoriesRecyclerAdapter;
import alexey.githubsearchapp.android.models.GitHubRepository;
import alexey.githubsearchapp.android.models.SearchRepositoriesResponse;
import alexey.githubsearchapp.android.presenters.base.BasePresenter;
import alexey.githubsearchapp.android.retrofit.api.GitHubApi;
import alexey.githubsearchapp.android.utils.Constants;
import alexey.githubsearchapp.android.utils.Utils;
import alexey.githubsearchapp.android.views.base.iOnRepoItemClickListener;
import alexey.githubsearchapp.android.views.fragments.iRepoListView;
import alexeys.rxnetwork.RxNetwork;
import lombok.Getter;
import lombok.Setter;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ally on 25/01/18.
 */

public class RepoListPresenter extends BasePresenter implements iRepoListPresenter {

    private static final String TAG = "RepoDetailsPresenter";

    private iRepoListView mView;
    private Context mContext;
    private GitHubApi mGitHubApi;

    private RepositoriesRecyclerAdapter mRecyclerAdapter;

    private Subscription getRepoSubscription;

    protected int currentPage = 0;
    protected int totalPages = 0;
    protected boolean loadingNow = false;
    protected int totalItemsCount = 0;

    @Setter
    @Getter
    private String currentSearchText;

    public List<GitHubRepository> getGitHubRepositories() {
        return mRecyclerAdapter.getGitHubRepositories();
    }

    @Inject
    public RepoListPresenter(iRepoListView view, Context context, GitHubApi gitHubApi , RxNetwork rxNetwork) {
        this.mView = view;
        this.mContext = context;
        this.mGitHubApi = gitHubApi;
        this.mRxNetwork = rxNetwork;

        mRecyclerAdapter = new RepositoriesRecyclerAdapter(mContext, (iOnRepoItemClickListener) mView);
    }

    public void setExistingListForAdapter() {
        mRecyclerAdapter.addGitHubRepositoriesList(Utils.getGitHubRepositories());
    }


    @Override
    public void init() {
        currentPage = 1;
        subscribeForInternetStateChanges();
    }

    @Override
    public void onDestroy() {
        unsubscribe(getRepoSubscription);
        unsubscribe(internetStateChangesSubscription);
    }



    @Override
    public void loadGitHubRepositories(String searchingText) {
        if(getRepoSubscription != null && !getRepoSubscription.isUnsubscribed()) {
            return;
        }

        if (!mRxNetwork.isInternetConnected()) {
            mView.onNoInternet();
            return;
        }

        currentSearchText = searchingText;
        mView.showLoadingProcess();

        getRepoSubscription = mGitHubApi
                .getRepositories(searchingText,
                        Constants.API_REQUEST_PARAM_SORT_VALUE + Constants.API_REQUEST_PARAM_Q_ADD_PARAM,
                        Constants.itemsPerPage,
                        this.currentPage)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchRepositoriesResponse>() {
                    @Override
                    public final void onCompleted() {
                        mView.hideLoadingProcess();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.d(TAG, "Error downloading repositories: " + e.getMessage());
                        mView.hideLoadingProcess();
                        mView.onRepositoriesLoadFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(SearchRepositoriesResponse response) {
                        Log.d(TAG, "Download succeed");
                        loadingNow = false;
                        countTotalPages(response.getTotalItemsСount());
                        displayRepositories(response.getGitHubRepositories());
                        mView.hideLoadingProcess();
                    }
        });
    }

    public void displayRepositories(List<GitHubRepository> list) {
        if (currentPage == 1) {
            mRecyclerAdapter.clear();
        }

        mRecyclerAdapter.addGitHubRepositoriesList(list);
        mView.onRepositoriesLoadSuccessfully();

        if (currentPage == 1 && list.size() == 0) {
            onEmptyList();
        }
    }

    public void clearRepositories() {
        mRecyclerAdapter.clear();
    }

    @Override
    public void searchRefresh(String searchingText) {
        currentPage = 1;
        this.clearRepositories();
        this.loadGitHubRepositories(searchingText);
    }


    @Override
    public void onEmptyList() {
        currentPage = 1;
        totalPages = 0;
        this.clearRepositories();
        mView.hideLoadingProcess();
        mView.onRepositoriesLoadFailed(mContext.getString(R.string.no_results));
    }

    @Override
    public RepositoriesRecyclerAdapter gerRepositoriesRecyclerAdapter() {
        return mRecyclerAdapter;
    }

    @Override
    public boolean isLoadingNow() {
        return loadingNow;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public void setLoadingNow(boolean loading) {
        this.loadingNow = loading;
    }

    @Override
    public void incrementPageNumber() {
        currentPage++;
    }

    private void countTotalPages(int itemsCount) {
        int result = 0;

        this.totalItemsCount = itemsCount;
        result = itemsCount / Constants.itemsPerPage;

        if (itemsCount % Constants.itemsPerPage != 0) {
            result++;
        }

        this.totalPages = result;
    }



    @Override
    protected void onNoInternetConnection() {
        super.onNoInternetConnection();
        mView.onNoInternet();
    }
}
