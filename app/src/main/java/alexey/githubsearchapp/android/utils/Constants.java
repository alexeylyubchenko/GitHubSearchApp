package alexey.githubsearchapp.android.utils;

import alexey.githubsearchapp.android.R;

/**
 * Created by Alexey on 25/01/18.
 */

public class Constants {

    //fragments
    public static final String FRAGMENT_REPO_LIST_FRAGMENT_TAG = "FRAGMENT_REPO_LIST_FRAGMENT";
    public static final String FRAGMENT_REPO_DETAILS_FRAGMENT_TAG = "FRAGMENT_REPO_DETAILS_FRAGMENT";
    public static final String REPO_DETAILS_ARGS_LOGIN = "REPO_DETAILS_ARGS_LOGIN";

    //system settings
    public static final int itemsPerPage = 10;
    public static final String SAVE_STATE_CURRENT_PAGE_KEY = "save_state_current_page";
    public static final String SAVE_STATE_SEARCH_TEXT_KEY = "save_state_current_page";

    public static final int FRAGMENT_PLACE_HOLDER_ID = R.id.frame_holder;

    //api https://api.github.com/search/repositories?q=test&per_page=5&page=1
    public static final String API_URL = "https://api.github.com";
    public static final String API_REQUEST_SEARCH_CMD = "/search/repositories";
    public static final String API_REQUEST_FOLLOWERS_CMD = "users/{login}/followers";

    public static final String API_REQUEST_FOLLOWERS_PARAM_LOGIN = "login";
    public static final String API_REQUEST_PARAM_Q_KEY = "q";
    public static final String API_REQUEST_PARAM_Q_ADD_PARAM = "+language:assembly";
    public static final String API_REQUEST_PARAM_SORT_KEY = "sort";
    public static final String API_REQUEST_PARAM_SORT_VALUE = "forks";

    public static final String API_REQUEST_PARAM_PAGE_KEY = "page";
    public static final String API_REQUEST_PARAM_PER_PAGE_KEY = "per_page";

    //json
    public static final String JSON_PARAM_TOTALCOUNT = "total_count";
    public static final String JSON_PARAM_ITEMS = "items";
    public static final String JSON_PARAM_ID = "id";
    public static final String JSON_PARAM_NAME = "name";
    public static final String JSON_PARAM_DESC = "description";
    public static final String JSON_PARAM_FORKSCOUNT = "forks_count";
    public static final String JSON_PARAM_OWNER = "owner";
    public static final String JSON_PARAM_OWNER_ID = "id";
    public static final String JSON_PARAM_OWNER_AVATAR = "avatar_url";
    public static final String JSON_PARAM_OWNER_LOGIN = "login";

    public static final String JSON_PARAM_FOLLOWERS_LOGIN = "login";
    public static final String JSON_PARAM_FOLLOWERS_ID = "id";
    public static final String JSON_PARAM_FOLLOWERS_AVATAR = "avatar_url";

}
