package alexey.githubsearchapp.android.views.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by ally on 25/01/18.
 */

public class BaseActivity extends AppCompatActivity {

    protected void switchToFragment(FragmentManager fragmentManager, int contentId, Fragment pFragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(contentId, pFragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    protected void showLoadingInView(final ProgressBar progressBar, final boolean show) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int animationTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            progressBar.animate()
                    .setDuration(animationTime).alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                        }
                    });

        } else {
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

}
