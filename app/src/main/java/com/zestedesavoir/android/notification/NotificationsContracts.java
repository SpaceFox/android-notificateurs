package com.zestedesavoir.android.notification;

import com.zestedesavoir.android.internal.ui.BasePresenter;
import com.zestedesavoir.android.internal.ui.BaseView;
import com.zestedesavoir.android.notification.models.Notification;

import java.util.List;

interface NotificationsContracts {
    interface View extends BaseView<Presenter> {
        void updateNotifications(List<Notification> notifications);

        void showError(Throwable throwable);

        void showProgress(final boolean show);
    }

    interface Presenter extends BasePresenter {
        void getNotifications(int page);
    }
}