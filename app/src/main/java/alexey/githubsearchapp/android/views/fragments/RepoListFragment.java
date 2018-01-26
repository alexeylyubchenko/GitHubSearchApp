package alexey.githubsearchapp.android.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;

import alexey.githubsearchapp.android.R;
import alexey.githubsearchapp.android.engine.app.AppEngine;
import alexey.githubsearchapp.android.engine.dagger.components.DaggerRepoListViewComponent;
import alexey.githubsearchapp.android.engine.dagger.components.RepoListViewComponent;
import alexey.githubsearchapp.android.engine.dagger.modules.views.RepoListViewModule;
import alexey.githubsearchapp.android.presenters.RepoListPresenter;
import alexey.githubsearchapp.android.utils.Constants;
import alexey.githubsearchapp.android.utils.Utils;
import alexey.githubsearchapp.android.views.base.BaseFragment;
import alexey.githubsearchapp.android.views.base.iOnRepoItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class RepoListFragment extends BaseFragment implements iRepoListView, SearchView.OnQueryTextListener, iOnRepoItemClickListener {

    Unbinder mUnbinder;

    @BindView(R.id.githubRecyclerView)
    RecyclerView mRecyclerView;

    @Inject
    RepoListPresenter mRepoListPresenter;


    RepoListViewComponent mRepoListViewComponent;

    @BindView(R.id.searchView)
    SearchView searchView;

    private String mCurrentSearchText;
    private LinearLayoutManager llm;
    private boolean mRestored = false;

    public RepoListFragment() {
    }

    public static RepoListFragment newInstance() {
        RepoListFragment fragment = new RepoListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);

        injectDagger();
        mRepoListPresenter.init();
        setUpView(view);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Utils.setGitHubRepositories(mRepoListPresenter.getGitHubRepositories());
        outState.putInt(Constants.SAVE_STATE_CURRENT_PAGE_KEY, mRepoListPresenter.getCurrentPage());
        outState.putString(Constants.SAVE_STATE_SEARCH_TEXT_KEY, mRepoListPresenter.getCurrentSearchText());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            try {
                mRepoListPresenter.setCurrentPage(savedInstanceState.getInt(Constants.SAVE_STATE_CURRENT_PAGE_KEY));
                String searchToken = savedInstanceState.getString(Constants.SAVE_STATE_SEARCH_TEXT_KEY);
                mRepoListPresenter.setCurrentSearchText(searchToken);
                mRepoListPresenter.setExistingListForAdapter();
                mRestored = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void injectDagger() {
        mRepoListViewComponent = DaggerRepoListViewComponent.builder()
                .appComponent(AppEngine.getAppComponent())
                .repoListViewModule(new RepoListViewModule(this))
                .build();
        mRepoListViewComponent.injectFragment(this);
    }

    @Override
    public void setUpView(View view) {
        mUnbinder = ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);
        llm = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mRepoListPresenter.gerRepositoriesRecyclerAdapter());

        searchView.setOnQueryTextListener(this);
        String searchToken = mRepoListPresenter.getCurrentSearchText();
        if(searchToken != null && !searchToken.isEmpty()) {
            searchView.setQuery(searchToken, false);
        }

        setUpScrollingView();
    }

    @Override
    public void showLoadingProcess() {
        showLoadingInMainActivity(true);
    }

    @Override
    public void hideLoadingProcess() {
        showLoadingInMainActivity(false);
    }

    @Override
    public void onRepositoriesLoadSuccessfully() {

    }


    @Override
    public void onRepositoriesLoadFailed(String message) {
        showSnackbarMessage(message);
    }

    @Override
    public void setUpScrollingView() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int pastVisiblesItems, visibleItemCount, totalItemCount;
                if (!mRepoListPresenter.isLoadingNow()) {
                    if (dy > 0) {
                        visibleItemCount = llm.getChildCount();
                        totalItemCount = llm.getItemCount();
                        pastVisiblesItems = llm.findFirstVisibleItemPosition();

                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            mRepoListPresenter.setLoadingNow(true);
                            mRepoListPresenter.incrementPageNumber();
                            mRepoListPresenter.loadGitHubRepositories(mCurrentSearchText);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onNoInternet() {
        showSnackbarMessage(getString(R.string.no_internet));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mRepoListPresenter.onDestroy();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(!mRestored) {
            mCurrentSearchText = newText;
            mRepoListPresenter.searchRefresh(newText);
        }

        mRestored = false;
        return true;
    }


    @Override
    public void onItemClicked(String login) {
        switchToFragment(RepoDetailsFragment.newInstance(login), Constants.FRAGMENT_REPO_DETAILS_FRAGMENT_TAG);
    }

    @OnClick(R.id.searchView)
    public void searchView_onClicked() {
        searchView.setIconified(false);
    }
}
