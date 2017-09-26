package com.example.android.yourcity.ui.base;

import android.app.ProgressDialog;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.android.yourcity.R;

public class BaseActivity extends MvpAppCompatActivity implements BaseView {

    private ProgressDialog progressDialog;

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showProgress(boolean inProgress) {
        if (inProgress) {
            if (progressDialog != null) {
                progressDialog.show();
            } else {
                progressDialog = ProgressDialog.show(this, null, "Please wait while download will be success", true, false);
                progressDialog.show();
            }
        } else {
            if (progressDialog != null) progressDialog.dismiss();
        }
    }
}
