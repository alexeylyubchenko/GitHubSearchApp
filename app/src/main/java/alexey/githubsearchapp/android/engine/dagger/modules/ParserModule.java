package alexey.githubsearchapp.android.engine.dagger.modules;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ally on 14/11/17.
 */

@Module
public class ParserModule {

    @Provides
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }

}
