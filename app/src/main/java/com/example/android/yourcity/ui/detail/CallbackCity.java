package com.example.android.yourcity.ui.detail;

public interface CallbackCity {

    void onSuccess(String cityDescription);

    void onFailure(String errorMessage);
}
