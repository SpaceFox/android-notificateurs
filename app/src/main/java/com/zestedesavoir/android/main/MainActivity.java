package com.zestedesavoir.android.main;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zestedesavoir.android.OnNavigationListener;
import com.zestedesavoir.android.R;
import com.zestedesavoir.android.ZdSApplication;
import com.zestedesavoir.android.login.LoginFragment;
import com.zestedesavoir.android.notification.NotificationsFragment;
import com.zestedesavoir.android.notification.services.NotificationService;
import com.zestedesavoir.android.notification.services.StarterReceiver;
import com.zestedesavoir.android.settings.SettingsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnNavigationListener, MainContracts.View {

    @Inject
    MainContracts.Presenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        ((ZdSApplication) getApplicationContext()).getAppComponent().plus(new MainPresenterModule(this)).inject(this);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.subscribe();
        presenter.isAuthenticated();
        final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(NotificationService.NOTIFICATION_ID);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sendBroadcast(StarterReceiver.getStartIntent(getApplicationContext()));
    }

    @Override
    public void isDisconnected() {
        toolbar.setVisibility(View.GONE);
        goTo(LoginFragment.newInstance(), LoginFragment.TAG, null);
    }

    @Override
    public void goToLoginScreen() {
        presenter.disconnect();
    }

    @Override
    public void goToNotificationScreen() {
        toolbar.setVisibility(View.VISIBLE);
        goTo(NotificationsFragment.newInstance(), NotificationsFragment.TAG, null);
    }

    @Override
    public void goToSettingsScreen() {
        toolbar.setVisibility(View.VISIBLE);
        goTo(SettingsFragment.newInstance(), SettingsFragment.TAG);
    }

    @Override
    public void back() {
        getSupportFragmentManager().popBackStack();
    }

    private void goTo(Fragment fragment, String tag) {
        goTo(fragment, tag, tag);
    }

    private void goTo(Fragment fragment, String tag, String nameStack) {
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment, tag);
        if (nameStack != null) {
            ft.addToBackStack(nameStack);
        }
        ft.commit();
    }
}
