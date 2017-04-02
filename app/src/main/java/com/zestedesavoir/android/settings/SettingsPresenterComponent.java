package com.zestedesavoir.android.settings;

import dagger.Subcomponent;

@SettingsPresenterScope
@Subcomponent(modules = SettingsPresenterModule.class)
public interface SettingsPresenterComponent {
    void inject(SettingsFragment fragment);
}
