package alexey.githubsearchapp.android.engine.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ally on 08/11/17.
 */

@Module
public class RetrofitModule {


    @Provides
    @Singleton
    public RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

}
