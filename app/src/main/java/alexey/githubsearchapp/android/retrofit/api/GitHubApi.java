package alexey.githubsearchapp.android.retrofit.api;

import java.util.List;

import alexey.githubsearchapp.android.models.Follower;
import alexey.githubsearchapp.android.models.SearchRepositoriesResponse;
import alexey.githubsearchapp.android.utils.Constants;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ally on 25/01/18.
 */

public interface GitHubApi {
    @GET(Constants.API_REQUEST_SEARCH_CMD)
    Observable<SearchRepositoriesResponse> getRepositories
            (@Query(Constants.API_REQUEST_PARAM_Q_KEY) String text,
             @Query(Constants.API_REQUEST_PARAM_SORT_KEY) String sort,
             @Query(Constants.API_REQUEST_PARAM_PER_PAGE_KEY) int per_page,
             @Query(Constants.API_REQUEST_PARAM_PAGE_KEY) int page);

    @GET(Constants.API_REQUEST_FOLLOWERS_CMD)
    Observable<List<Follower>> getFollowers(@Path(Constants.API_REQUEST_FOLLOWERS_PARAM_LOGIN) String login);
}
