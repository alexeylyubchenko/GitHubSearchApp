package alexey.githubsearchapp.android.presenters.base;

import alexeys.rxnetwork.RxNetwork;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ally on 25/01/18.
 */

public class BasePresenter {

    protected RxNetwork mRxNetwork;
    protected Subscription internetStateChangesSubscription;

    protected void subscribeForInternetStateChanges() {
        internetStateChangesSubscription = mRxNetwork.observeOnInternetConnectionState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean isInternetConnected) {
                        if (!isInternetConnected) {
                            onNoInternetConnection();
                        }
                    }
                });
    }

    protected void onNoInternetConnection() {
    }

    protected void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
