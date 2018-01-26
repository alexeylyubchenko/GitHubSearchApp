package alexey.githubsearchapp.android.presenters;

import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import alexey.githubsearchapp.android.R;
import alexey.githubsearchapp.android.adapters.FollowersRecyclerAdapter;
import alexey.githubsearchapp.android.models.Follower;
import alexey.githubsearchapp.android.presenters.base.BasePresenter;
import alexey.githubsearchapp.android.retrofit.api.GitHubApi;
import alexey.githubsearchapp.android.views.fragments.iRepoDetailsView;
import alexeys.rxnetwork.RxNetwork;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ally on 25/01/18.
 */

public class RepoDetailsPresenter extends BasePresenter implements iRepoDetailsPresenter {

    private static final String TAG = "RepoDetailsPresenter";

    private iRepoDetailsView mView;
    private Context mContext;
    private GitHubApi mGitHubApi;


    FollowersRecyclerAdapter mFollowersRecyclerAdapter;

    Subscription getFollowersSubscription;

    @Inject
    public RepoDetailsPresenter(Context context, iRepoDetailsView view, GitHubApi gitHubApi, RxNetwork rxNetwork) {
        this.mView = view;
        this.mContext = context;
        this.mGitHubApi = gitHubApi;
        this.mRxNetwork = rxNetwork;
    }

    @Override
    public void init() {
        mFollowersRecyclerAdapter = new FollowersRecyclerAdapter(mContext);
        subscribeForInternetStateChanges();
    }

    @Override
    public void onDestroy() {
        unsubscribe(getFollowersSubscription);
    }

    @Override
    public void loadSubscriptionDetails(String login) {
        if(getFollowersSubscription != null && !getFollowersSubscription.isUnsubscribed()) {
            return;
        }

        if (!mRxNetwork.isInternetConnected()) {
            mView.onNoInternet();
            return;
        }

        mView.showLoadingProcess();

        getFollowersSubscription = mGitHubApi.getFollowers(login)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Follower>>() {
                    @Override
                    public final void onCompleted() {
                        mView.hideLoadingProcess();
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.d(TAG, "Error downloading followers: " + e.getMessage());
                        mView.hideLoadingProcess();
                        mView.onFollowersLoadFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Follower> response) {
                        Log.d(TAG, "Download succeed");
                        displayFollowers(response);
                        mView.hideLoadingProcess();
                    }
                });
    }

    private void displayFollowers(List<Follower> list) {
        mFollowersRecyclerAdapter.addFollowerList(list);

        if(list.size() == 0) {
            mView.onFollowersLoadFailed(mContext.getString(R.string.no_results));
        }

        unsubscribe(getFollowersSubscription);
    }

    @Override
    protected void onNoInternetConnection() {
        super.onNoInternetConnection();
        mView.onNoInternet();
    }

    @Override
    public FollowersRecyclerAdapter getFollowersRecyclerAdapter() {
        return mFollowersRecyclerAdapter;
    }
}

