package alexey.githubsearchapp.android.views.fragments;

import alexey.githubsearchapp.android.views.base.iBaseViewOps;

/**
 * Created by ally on 25/01/18.
 */

public interface iRepoDetailsView extends iBaseViewOps {
    void onFollowersLoadSuccessfully();
    void onFollowersLoadFailed(String message);
}
