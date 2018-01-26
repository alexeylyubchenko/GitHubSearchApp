package alexey.githubsearchapp.android.engine.dagger.components;

import alexey.githubsearchapp.android.engine.dagger.modules.views.MainActivityViewModule;
import alexey.githubsearchapp.android.engine.dagger.scopes.ActivityScope;
import alexey.githubsearchapp.android.views.activities.MainActivity;
import dagger.Component;

/**
 * Created by ally on 25/01/18.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {MainActivityViewModule.class})
public interface MainActivityComponent {
    void injectActivity(MainActivity activity);
}
