package com.zestedesavoir.android.settings;

import com.zestedesavoir.android.login.managers.Session;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsPresenterModule {
    private final SettingsContracts.View view;

    SettingsPresenterModule(SettingsContracts.View view) {
        this.view = view;
    }

    @Provides
    @SettingsPresenterScope
    SettingsContracts.View provideView() {
        return view;
    }


    @Provides
    @SettingsPresenterScope
    SettingsContracts.Presenter providePresenter(SettingsContracts.View view, Session session) {
        return new SettingsPresenter(view, session);
    }
}
