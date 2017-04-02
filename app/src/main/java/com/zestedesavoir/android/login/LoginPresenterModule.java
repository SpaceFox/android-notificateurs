package com.zestedesavoir.android.login;

import com.zestedesavoir.android.login.managers.Session;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginPresenterModule {
    private final LoginContracts.View view;

    LoginPresenterModule(LoginContracts.View view) {
        this.view = view;
    }

    @Provides
    @LoginPresenterScope
    LoginContracts.View provideView() {
        return view;
    }


    @Provides
    @LoginPresenterScope
    LoginContracts.Presenter providePresenter(LoginContracts.View view, Session session) {
        return new LoginPresenter(view, session);
    }
}
