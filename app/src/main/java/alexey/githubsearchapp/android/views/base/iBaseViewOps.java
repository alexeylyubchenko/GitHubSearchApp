package alexey.githubsearchapp.android.views.base;

import android.view.View;

/**
 * Created by ally on 09/11/17.
 */

public interface iBaseViewOps extends iBaseGeneralOps {
    void setUpView(View view);
    void showLoadingProcess();
    void hideLoadingProcess();
    void onNoInternet();
}
