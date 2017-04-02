package com.zestedesavoir.android.notification;

import com.zestedesavoir.android.notification.managers.NotificationsManager;

import dagger.Module;
import dagger.Provides;

@Module
public class NotificationsPresenterModule {
    private final NotificationsContracts.View view;

    NotificationsPresenterModule(NotificationsContracts.View view) {
        this.view = view;
    }

    @Provides
    @NotificationsPresenterScope
    NotificationsContracts.View provideView() {
        return view;
    }


    @Provides
    @NotificationsPresenterScope
    NotificationsContracts.Presenter providePresenter(NotificationsContracts.View view, NotificationsManager manager) {
        return new NotificationsPresenter(view, manager);
    }
}
