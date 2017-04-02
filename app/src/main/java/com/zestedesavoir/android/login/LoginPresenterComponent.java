package com.zestedesavoir.android.login;

import dagger.Subcomponent;

@LoginPresenterScope
@Subcomponent(modules = LoginPresenterModule.class)
public interface LoginPresenterComponent {
    void inject(LoginFragment fragment);
}
