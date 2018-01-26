package alexey.githubsearchapp.android.engine.dagger.modules.views;

import alexey.githubsearchapp.android.engine.dagger.scopes.PerFragmentScope;
import alexey.githubsearchapp.android.views.fragments.iRepoListView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ally on 25/01/18.
 */

@Module
public class RepoListViewModule {

    iRepoListView mView;

    public RepoListViewModule(iRepoListView view) {
        this.mView = view;
    }

    @Provides
    @PerFragmentScope
    public iRepoListView provideRepoListView() {
        return mView;
    }
}
