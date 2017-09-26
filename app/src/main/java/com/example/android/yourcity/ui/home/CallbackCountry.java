package com.example.android.yourcity.ui.home;

import java.util.List;

public interface CallbackCountry {

    void onSuccess(List<String> countries);

    void onFailure(String errorMessage);
}
