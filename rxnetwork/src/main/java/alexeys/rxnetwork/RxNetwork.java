package alexeys.rxnetwork;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

/**
 * Created by ally on 25/01/18.
 */

public class RxNetwork {

    private ConnectivityManager mConnectivityManager;
    private Context mContext;

    public RxNetwork(Context context, ConnectivityManager connectivityManager) {
        this.mContext = context;
        this.mConnectivityManager = connectivityManager;
    }

    public boolean isInternetConnected() {
        try {
            NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        } catch (Exception exp) {
            Log.e(getClass().getName(), exp.getMessage());
            return false;
        }

    }

    public Observable<Boolean> observeOnInternetConnectionState() {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

                final BroadcastReceiver networkStateChangesReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        subscriber.onNext(isInternetConnected());
                    }
                };

                mContext.registerReceiver(networkStateChangesReceiver, intentFilter);
                subscriber.add(unsubsribeInUIThread(new Action0() {
                    @Override
                    public void call() {
                        if(networkStateChangesReceiver != null) {
                            mContext.unregisterReceiver(networkStateChangesReceiver);
                        }
                    }
                }));


            }
        });
    }

    private Subscription unsubsribeInUIThread(final Action0 pAction) {
        return Subscriptions.create(new Action0() {
            @Override
            public void call() {
                if(Looper.getMainLooper() == Looper.myLooper()) {
                    pAction.call();
                } else {
                    final rx.Scheduler.Worker worker = AndroidSchedulers.mainThread().createWorker();
                    worker.schedule(new Action0() {
                        @Override
                        public void call() {
                            pAction.call();
                            worker.unsubscribe();
                        }
                    });
                }
            }
        });
    }

}
