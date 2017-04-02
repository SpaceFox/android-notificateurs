package com.zestedesavoir.android.main;

import dagger.Subcomponent;

@MainPresenterScope
@Subcomponent(modules = MainPresenterModule.class)
public interface MainPresenterComponent {
    void inject(MainActivity activity);
}
