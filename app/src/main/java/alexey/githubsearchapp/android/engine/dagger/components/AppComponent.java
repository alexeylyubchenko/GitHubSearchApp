package alexey.githubsearchapp.android.engine.dagger.components;

import android.app.Application;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Singleton;

import alexey.githubsearchapp.android.engine.dagger.modules.AppModule;
import alexey.githubsearchapp.android.engine.dagger.modules.GitHubApiModule;
import alexey.githubsearchapp.android.engine.dagger.modules.NetworkModule;
import alexey.githubsearchapp.android.engine.dagger.modules.ParserModule;
import alexey.githubsearchapp.android.engine.dagger.modules.RetrofitModule;
import alexey.githubsearchapp.android.retrofit.api.GitHubApi;
import dagger.Component;
import alexeys.rxnetwork.RxNetwork;

/**
 * Created by ally on 04/11/17.
 */

@Singleton
@Component(modules = {AppModule.class, GitHubApiModule.class, NetworkModule.class, ParserModule.class, RetrofitModule.class
})
public interface AppComponent {

    Context getContext();
    GitHubApi getGitHubApi();
    RxNetwork getRxNetwork();

    void injectApplication(Application application);
}
