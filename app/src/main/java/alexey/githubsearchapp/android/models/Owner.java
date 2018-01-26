package alexey.githubsearchapp.android.models;

import com.google.gson.annotations.SerializedName;

import alexey.githubsearchapp.android.utils.Constants;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by ally on 25/01/18.
 */

public class Owner {
    @Getter @Setter
    @SerializedName(Constants.JSON_PARAM_OWNER_ID)
    private String id;

    @Getter @Setter
    @SerializedName(Constants.JSON_PARAM_OWNER_AVATAR)
    private String avatarUrl;

    @Getter @Setter
    @SerializedName(Constants.JSON_PARAM_OWNER_LOGIN)
    private String login;

}
