package com.zestedesavoir.android.main;

import com.zestedesavoir.android.login.managers.Session;
import com.zestedesavoir.android.notification.managers.NotificationsManager;

import dagger.Module;
import dagger.Provides;

@Module
public class MainPresenterModule {
    private final MainContracts.View view;

    MainPresenterModule(MainContracts.View view) {
        this.view = view;
    }

    @Provides
    @MainPresenterScope
    MainContracts.View provideView() {
        return view;
    }


    @Provides
    @MainPresenterScope
    MainContracts.Presenter providePresenter(MainContracts.View view, Session session, NotificationsManager manager) {
        return new MainPresenter(view, session, manager);
    }
}
