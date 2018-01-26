package alexey.githubsearchapp.android.engine.dagger.modules.views;

import android.support.v4.app.FragmentManager;

import alexey.githubsearchapp.android.views.activities.MainActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by ally on 25/01/18.
 */

@Module
public class MainActivityViewModule {
    private MainActivity mMainActivity;

    public MainActivityViewModule(MainActivity mainActivity) {
        this.mMainActivity = mainActivity;
    }

    @Provides
    public FragmentManager provideFragmentManager() {
        return this.mMainActivity.getSupportFragmentManager();
    }
}
