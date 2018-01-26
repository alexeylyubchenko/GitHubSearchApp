package alexey.githubsearchapp.android.views.base;

/**
 * Created by ally on 25/01/18.
 */

public interface iFragmentContainsViewOps {
    void switchToFragment(BaseFragment pFragment, String tag);
    void showLoading(boolean show);
    void setTitle(String title);
}
