package alexey.githubsearchapp.android.presenters;

import alexey.githubsearchapp.android.adapters.RepositoriesRecyclerAdapter;
import alexey.githubsearchapp.android.presenters.base.iBasePresenter;

/**
 * Created by ally on 25/01/18.
 */

public interface iRepoListPresenter extends iBasePresenter {
    void loadGitHubRepositories(String searchingText);
    void searchRefresh(String searchingTex);
    void onEmptyList();
    RepositoriesRecyclerAdapter gerRepositoriesRecyclerAdapter();
    boolean isLoadingNow();
    int getCurrentPage();
    void setCurrentPage(int currentPage);
    void setLoadingNow(boolean loading);
    void incrementPageNumber();
}
