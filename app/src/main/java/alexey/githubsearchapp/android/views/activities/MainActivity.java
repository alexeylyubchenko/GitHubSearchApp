package alexey.githubsearchapp.android.views.activities;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ProgressBar;

import javax.inject.Inject;

import alexey.githubsearchapp.android.R;
import alexey.githubsearchapp.android.engine.app.AppEngine;
import alexey.githubsearchapp.android.engine.dagger.components.DaggerMainActivityComponent;
import alexey.githubsearchapp.android.engine.dagger.components.MainActivityComponent;
import alexey.githubsearchapp.android.engine.dagger.modules.views.MainActivityViewModule;
import alexey.githubsearchapp.android.utils.Constants;
import alexey.githubsearchapp.android.views.base.BaseActivity;
import alexey.githubsearchapp.android.views.base.BaseFragment;
import alexey.githubsearchapp.android.views.base.iBaseActivityOps;
import alexey.githubsearchapp.android.views.base.iFragmentContainsViewOps;
import alexey.githubsearchapp.android.views.fragments.RepoListFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements iBaseActivityOps, iFragmentContainsViewOps {

    MainActivityComponent mMainActivityComponent;

    @Inject
    FragmentManager mFragmentManager;

    @BindView(R.id.processLoadingBar)
    ProgressBar processLoadingBar;

    ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        injectDagger();
        setUpView();
    }


    @Override
    public void injectDagger() {
        mMainActivityComponent = DaggerMainActivityComponent.builder()
                .appComponent(AppEngine.getAppComponent())
                .mainActivityViewModule(new MainActivityViewModule(this))
                .build();
        mMainActivityComponent.injectActivity(this);
    }

    @Override
    public void setUpView() {
        ButterKnife.bind(this);
        mActionBar = getSupportActionBar();
        switchToFragment(RepoListFragment.newInstance(), Constants.FRAGMENT_REPO_LIST_FRAGMENT_TAG);
    }

    @Override
    public void switchToFragment(BaseFragment pFragment, String tag) {


        switchToFragment(
                mFragmentManager,
                Constants.FRAGMENT_PLACE_HOLDER_ID,
                pFragment,
                tag);

    }

    @Override
    public void showLoading(boolean show) {
        processLoadingBar.bringToFront();
        super.showLoadingInView(processLoadingBar, show);
    }


    private void changeAppBar(String title, boolean showBackButton) {
        mActionBar.setDisplayHomeAsUpEnabled(showBackButton);
        mActionBar.setHomeButtonEnabled(showBackButton);
        mActionBar.setTitle(title);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        RepoListFragment fragment
                = (RepoListFragment) mFragmentManager.findFragmentByTag(Constants.FRAGMENT_REPO_LIST_FRAGMENT_TAG);

        if(fragment != null && fragment.isVisible()) {
            changeAppBar(getString(R.string.app_name), false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setTitle(String title) {
        changeAppBar(title, true);
    }
}
