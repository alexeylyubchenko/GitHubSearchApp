package alexey.githubsearchapp.android.models;

import com.google.gson.annotations.SerializedName;

import alexey.githubsearchapp.android.utils.Constants;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by ally on 25/01/18.
 */

public class Follower {

    @Getter @Setter
    @SerializedName(Constants.JSON_PARAM_FOLLOWERS_ID)
    private int id;

    @Getter @Setter
    @SerializedName(Constants.JSON_PARAM_FOLLOWERS_LOGIN)
    private String login;

    @Getter @Setter
    @SerializedName(Constants.JSON_PARAM_FOLLOWERS_AVATAR)
    private String avatarUrl;

}
