package com.zestedesavoir.android.login;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;

import com.zestedesavoir.android.R;
import com.zestedesavoir.android.internal.exceptions.RetrofitException;
import com.zestedesavoir.android.internal.ioc.AppComponent;
import com.zestedesavoir.android.internal.ui.AbsFragment;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginFragment extends AbsFragment<LoginContracts.Presenter> implements LoginContracts.View {
    public static final String TAG = "LoginFragment";

    public static Fragment newInstance() {
        return new LoginFragment();
    }

    @BindView(R.id.et_login)
    TextInputEditText etLogin;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.btn_connect)
    Button btnConnect;

    @Override
    protected int getResLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void inject(AppComponent component) {
        component.plus(new LoginPresenterModule(this)).inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etPassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                trySubmit();
                return true;
            }
            return false;
        });
    }

    @OnClick(R.id.btn_connect)
    public void trySubmit() {
        if (!btnConnect.isEnabled()) {
            return;
        }

        etLogin.setError(null);
        etPassword.setError(null);

        btnConnect.setEnabled(false);
        presenter.connect(etLogin.getText().toString().trim(), etPassword.getText().toString().trim());
    }

    @Override
    public void showUsernameError() {
        etLogin.setError(getString(R.string.field_login));
        etLogin.requestFocus();
        btnConnect.setEnabled(true);
    }

    @Override
    public void showPasswordError() {
        etPassword.setError(getString(R.string.field_password));
        etPassword.requestFocus();
        btnConnect.setEnabled(true);
    }

    @Override
    public void showServerError(RetrofitException throwable) {
        Timber.e(throwable);
        Snackbar.make(getView(), R.string.alert_server_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void authenticated() {
        if (listener != null) {
            listener.goToNotificationScreen();
        }
    }
}
