package alexey.githubsearchapp.android.engine.dagger.modules;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;


import javax.inject.Singleton;

import alexey.githubsearchapp.android.engine.app.AppEngine;
import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private Context mContext;

    public AppModule(AppEngine appEngine) {
        mContext = appEngine.getApplicationContext();
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }


}
