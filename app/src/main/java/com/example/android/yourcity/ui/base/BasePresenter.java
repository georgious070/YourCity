package com.example.android.yourcity.ui.base;

import com.arellomobile.mvp.MvpPresenter;

public class BasePresenter<T extends BaseView> extends MvpPresenter<T>{

    void showError(Throwable t){
        getViewState().showToast(t.getMessage());
    }
}
