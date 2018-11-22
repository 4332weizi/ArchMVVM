package io.auxo.arch.sample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import io.auxo.arch.mvvm.view.activity.BaseActivity;
import io.auxo.arch.sample.login.LoginActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }, 3000);
    }
}
