package alexey.githubsearchapp.android.engine.dagger.modules;

import android.content.Context;
import android.net.ConnectivityManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import alexeys.rxnetwork.RxNetwork;


@Module
public class NetworkModule {

    @Provides
    @Singleton
    public ConnectivityManager provideConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    public RxNetwork provideRxNetwork(Context context, ConnectivityManager connectivityManager){
        return new RxNetwork(context, connectivityManager);
    }

}
