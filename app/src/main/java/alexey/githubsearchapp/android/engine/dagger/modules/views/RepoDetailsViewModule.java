package alexey.githubsearchapp.android.engine.dagger.modules.views;

import alexey.githubsearchapp.android.engine.dagger.scopes.PerFragmentScope;
import alexey.githubsearchapp.android.views.fragments.iRepoDetailsView;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ally on 25/01/18.
 */

@Module
public class RepoDetailsViewModule {

    iRepoDetailsView mView;

    public RepoDetailsViewModule(iRepoDetailsView view) {
        this.mView = view;
    }

    @Provides
    @PerFragmentScope
    public iRepoDetailsView provideRepoDetailsView() {
        return mView;
    }
}
