package alexey.githubsearchapp.android.engine.dagger.components;

import alexey.githubsearchapp.android.engine.dagger.modules.views.RepoListViewModule;
import alexey.githubsearchapp.android.engine.dagger.scopes.PerFragmentScope;
import alexey.githubsearchapp.android.views.fragments.RepoListFragment;
import dagger.Component;

/**
 * Created by ally on 25/01/18.
 */

@PerFragmentScope
@Component(dependencies = AppComponent.class, modules = {RepoListViewModule.class})
public interface RepoListViewComponent {
    void injectFragment(RepoListFragment fragment);
}
