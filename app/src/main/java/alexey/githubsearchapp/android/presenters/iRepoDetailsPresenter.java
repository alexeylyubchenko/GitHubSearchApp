package alexey.githubsearchapp.android.presenters;

import alexey.githubsearchapp.android.adapters.FollowersRecyclerAdapter;
import alexey.githubsearchapp.android.presenters.base.iBasePresenter;

/**
 * Created by ally on 25/01/18.
 */

public interface iRepoDetailsPresenter extends iBasePresenter {
    void loadSubscriptionDetails(String login);
    FollowersRecyclerAdapter getFollowersRecyclerAdapter();
}
