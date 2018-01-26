package alexey.githubsearchapp.android.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import alexey.githubsearchapp.android.R;
import alexey.githubsearchapp.android.engine.app.AppEngine;
import alexey.githubsearchapp.android.engine.dagger.components.DaggerRepoDetailsViewComponent;
import alexey.githubsearchapp.android.engine.dagger.components.RepoDetailsViewComponent;
import alexey.githubsearchapp.android.engine.dagger.modules.views.RepoDetailsViewModule;
import alexey.githubsearchapp.android.presenters.RepoDetailsPresenter;
import alexey.githubsearchapp.android.utils.Constants;
import alexey.githubsearchapp.android.views.base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RepoDetailsFragment extends BaseFragment implements iRepoDetailsView {

    private String loginParam;

    Unbinder mUnbinder;

    @Inject
    RepoDetailsPresenter mRepoDetailsPresenter;

    RepoDetailsViewComponent mRepoDetailsViewComponent;

    @BindView(R.id.followersRecyclerView)
    RecyclerView mRecyclerView;


    public static RepoDetailsFragment newInstance(String login) {
        RepoDetailsFragment fragment = new RepoDetailsFragment();
        Bundle args = new Bundle();
        args.putString(Constants.REPO_DETAILS_ARGS_LOGIN, login);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            loginParam = getArguments().getString(Constants.REPO_DETAILS_ARGS_LOGIN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_details, container, false);

        injectDagger();
        mRepoDetailsPresenter.init();
        setUpView(view);
        mRepoDetailsPresenter.loadSubscriptionDetails(loginParam);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void injectDagger() {
        mRepoDetailsViewComponent = DaggerRepoDetailsViewComponent.builder()
                .appComponent(AppEngine.getAppComponent())
                .repoDetailsViewModule(new RepoDetailsViewModule(this))
                .build();
        mRepoDetailsViewComponent.injectFragment(this);
    }

    @Override
    public void setUpView(View view) {
        mUnbinder = ButterKnife.bind(this, view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mRepoDetailsPresenter.getFollowersRecyclerAdapter());

        setTitleInMainActivity(loginParam);
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
    public void onNoInternet() {
        showSnackbarMessage(getString(R.string.no_internet));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mRepoDetailsPresenter.onDestroy();
    }

    @Override
    public void onFollowersLoadSuccessfully() {

    }

    @Override
    public void onFollowersLoadFailed(String message) {
        showSnackbarMessage(message);
    }
}
