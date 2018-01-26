package alexey.githubsearchapp.android.models;

import com.google.gson.annotations.SerializedName;

import alexey.githubsearchapp.android.utils.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by ally on 25/01/18.
 */

@NoArgsConstructor
public class GitHubRepository {

    @Getter @Setter
    @SerializedName(Constants.JSON_PARAM_ID)
    private int id;

    @Getter @Setter
    @SerializedName(Constants.JSON_PARAM_NAME)
    private String name;

    @Getter @Setter
    @SerializedName(Constants.JSON_PARAM_DESC)
    private String description;


    @Getter @Setter
    @SerializedName(Constants.JSON_PARAM_OWNER)
    private Owner owner;

    @Getter @Setter
    @SerializedName(Constants.JSON_PARAM_FORKSCOUNT)
    private int forksCount;

}

