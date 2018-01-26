package alexey.githubsearchapp.android.engine.app;

import android.app.Application;


import alexey.githubsearchapp.android.engine.dagger.components.AppComponent;
import alexey.githubsearchapp.android.engine.dagger.components.DaggerAppComponent;
import alexey.githubsearchapp.android.engine.dagger.modules.AppModule;

/**
 * Created by ally on 25/01/18.
 */

public class AppEngine extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        injectDagger();
    }


    private void injectDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.injectApplication(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
