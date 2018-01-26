package alexey.githubsearchapp.android.views.base;

import android.support.v4.app.Fragment;
import android.support.design.widget.Snackbar;

import alexey.githubsearchapp.android.R;

/**
 * Created by ally on 25/01/18.
 */

public class BaseFragment extends Fragment {

    protected void showSnackbarMessage(String message) {
        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(getContext().getResources().getColor(R.color.snack_bar_bg_color));
        snackbar.show();
    }

    protected void switchToFragment(BaseFragment pFragment, String tag) {
        if (getActivity() instanceof iFragmentContainsViewOps) {
            ((iFragmentContainsViewOps) getActivity()).switchToFragment(pFragment, tag);
        }
    }


    protected void showLoadingInMainActivity(boolean show) {
        if (getActivity() instanceof iFragmentContainsViewOps) {
            ((iFragmentContainsViewOps) getActivity()).showLoading(show);
        }
    }

    protected void setTitleInMainActivity(String title) {
        if (getActivity() instanceof iFragmentContainsViewOps) {
            ((iFragmentContainsViewOps) getActivity()).setTitle(title);
        }
    }

}
