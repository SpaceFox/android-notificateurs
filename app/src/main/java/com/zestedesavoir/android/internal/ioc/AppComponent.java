package com.zestedesavoir.android.internal.ioc;

import com.zestedesavoir.android.login.LoginManagerModule;
import com.zestedesavoir.android.login.LoginPresenterComponent;
import com.zestedesavoir.android.login.LoginPresenterModule;
import com.zestedesavoir.android.main.MainPresenterComponent;
import com.zestedesavoir.android.main.MainPresenterModule;
import com.zestedesavoir.android.notification.NotificationsManagerModule;
import com.zestedesavoir.android.notification.NotificationsPresenterComponent;
import com.zestedesavoir.android.notification.NotificationsPresenterModule;
import com.zestedesavoir.android.notification.daos.StateNotificationModule;
import com.zestedesavoir.android.notification.services.NotificationService;
import com.zestedesavoir.android.notification.services.OperationNotificationReceiver;
import com.zestedesavoir.android.settings.SettingsPresenterComponent;
import com.zestedesavoir.android.settings.SettingsPresenterModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class, NetworkModule.class, LoginManagerModule.class,
                NotificationsManagerModule.class, DatabaseModule.class,
                StateNotificationModule.class
        }
)
public interface AppComponent {
    void inject(NotificationService service);

    void inject(OperationNotificationReceiver receiver);

    MainPresenterComponent plus(MainPresenterModule module);

    LoginPresenterComponent plus(LoginPresenterModule module);

    NotificationsPresenterComponent plus(NotificationsPresenterModule module);

    SettingsPresenterComponent plus(SettingsPresenterModule module);
}
