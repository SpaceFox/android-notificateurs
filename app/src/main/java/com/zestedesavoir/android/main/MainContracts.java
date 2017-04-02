package com.zestedesavoir.android.main;

import com.zestedesavoir.android.internal.ui.BasePresenter;
import com.zestedesavoir.android.internal.ui.BaseView;

interface MainContracts {
    interface View extends BaseView<Presenter> {
        void goToLoginScreen();

        void goToNotificationScreen();

        void isDisconnected();
    }

    interface Presenter extends BasePresenter {
        void isAuthenticated();

        void disconnect();
    }
}
