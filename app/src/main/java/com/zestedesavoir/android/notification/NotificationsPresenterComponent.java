package com.zestedesavoir.android.notification;

import dagger.Subcomponent;

@NotificationsPresenterScope
@Subcomponent(modules = NotificationsPresenterModule.class)
public interface NotificationsPresenterComponent {
    void inject(NotificationsFragment fragment);
}
