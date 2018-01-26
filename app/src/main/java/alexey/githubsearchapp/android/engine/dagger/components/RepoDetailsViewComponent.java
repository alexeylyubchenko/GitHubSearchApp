package alexey.githubsearchapp.android.engine.dagger.components;

import alexey.githubsearchapp.android.engine.dagger.modules.views.RepoDetailsViewModule;
import alexey.githubsearchapp.android.engine.dagger.scopes.PerFragmentScope;
import alexey.githubsearchapp.android.views.fragments.RepoDetailsFragment;
import dagger.Component;

/**
 * Created by ally on 25/01/18.
 */

@PerFragmentScope
@Component(dependencies = AppComponent.class, modules = {RepoDetailsViewModule.class})
public interface RepoDetailsViewComponent {
    void injectFragment(RepoDetailsFragment fragment);
}
