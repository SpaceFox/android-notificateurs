package com.zestedesavoir.android.internal.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zestedesavoir.android.OnNavigationListener;
import com.zestedesavoir.android.ZdSApplication;
import com.zestedesavoir.android.internal.ioc.AppComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

public abstract class AbsFragment<T extends BasePresenter> extends Fragment implements BaseView<T> {
    @Inject
    protected T presenter;

    protected OnNavigationListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnNavigationListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement " + OnNavigationListener.class.getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View inflate = inflater.inflate(getResLayout(), container, false);
        ButterKnife.bind(this, inflate);
        inject(((ZdSApplication) getContext().getApplicationContext()).getAppComponent());
        return inflate;
    }

    @LayoutRes
    protected abstract int getResLayout();

    protected abstract void inject(AppComponent component);

    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.subscribe();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.unsubscribe();
        }
    }

}
