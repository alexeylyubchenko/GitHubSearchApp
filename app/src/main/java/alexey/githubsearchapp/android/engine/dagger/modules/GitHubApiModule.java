package alexey.githubsearchapp.android.engine.dagger.modules;


import javax.inject.Singleton;

import alexey.githubsearchapp.android.retrofit.api.GitHubApi;
import alexey.githubsearchapp.android.utils.Constants;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ally on 08/11/17.
 */

@Module
public class GitHubApiModule {

    @Provides
    @Singleton
    public GitHubApi provideGitHubApi(GsonConverterFactory gsonConverterFactory,
                                         RxJavaCallAdapterFactory rxJavaCallAdapterFactory) {
        Retrofit retrofitService = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();

        GitHubApi api = retrofitService.create(GitHubApi.class);

        return api;
    }

}
