package com.zestedesavoir.android.main;

import android.support.annotation.NonNull;

import com.zestedesavoir.android.internal.ui.AbsPresenter;
import com.zestedesavoir.android.login.managers.Session;
import com.zestedesavoir.android.notification.managers.NotificationsManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class MainPresenter extends AbsPresenter implements MainContracts.Presenter {
    @NonNull
    private final MainContracts.View view;

    @NonNull
    private final Session session;

    @NonNull
    private final NotificationsManager manager;

    MainPresenter(@NonNull MainContracts.View view, @NonNull Session session, @NonNull NotificationsManager manager) {
        super();
        this.view = view;
        this.session = session;
        this.manager = manager;
    }

    @Override
    public void isAuthenticated() {
        subscription.add(session.isAuthenticated()
                .flatMap(aBoolean -> manager.clear().map(aVoid -> aBoolean))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    if (!aBoolean) {
                        view.goToLoginScreen();
                    } else {
                        view.goToNotificationScreen();
                    }
                })
        );
    }

    @Override
    public void disconnect() {
        subscription.add(session.disconnect()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aVoid -> view.isDisconnected())
        );
    }
}
