package alexey.githubsearchapp.android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import alexey.githubsearchapp.android.utils.Constants;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Alexey on 25/01/18.
 */

public class SearchRepositoriesResponse {

    @Getter @Setter
    @SerializedName(Constants.JSON_PARAM_TOTALCOUNT)
    private int totalItemsСount;

    @Getter @Setter
    @SerializedName(Constants.JSON_PARAM_ITEMS)
    private List<GitHubRepository> gitHubRepositories;
}
