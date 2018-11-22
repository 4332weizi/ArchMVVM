package io.auxo.arch.sample.ui;

import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import io.auxo.arch.mvvm.view.activity.MvvmActivity;
import io.auxo.arch.sample.R;
import io.auxo.arch.sample.databinding.ActivitySplashBinding;
import io.auxo.arch.sample.ui.login.LoginFragment;

public class SplashActivity extends MvvmActivity<ActivitySplashBinding> {

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void bindViewModels() {
        new Handler().postDelayed(() -> {
            // Intent intent = new Intent(this, LoginActivity.class);
            // ActivityOptionsCompat options = ActivityOptionsCompat
            //         .makeSceneTransitionAnimation(this,
            //                 mGithubLogo,
            //                 ViewCompat.getTransitionName(mGithubLogo));
            // ActivityCompat.startActivity(this, intent, options.toBundle());
            showLoginFragment();
        }, 3000);
    }

    @Override
    public void registerViewEvents() {

    }

    @Override
    public void subscribeViewModelChanges() {

    }

    protected void showLoginFragment() {
        // add LoginFragment
        LoginFragment fragment = new LoginFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.splash_login, fragment)
                .commit();

        // disappear welcome and logo
        float disappearHeight = getBinding().splashWelcome.getMeasuredHeight() * 3 / 4f;
        float toYValue = disappearHeight / getBinding().getRoot().getMeasuredHeight();

        TranslateAnimation welcomeExit = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_PARENT, -toYValue);
        welcomeExit.setDuration(500);
        welcomeExit.setFillAfter(true);

        getBinding().splashWelcome.startAnimation(welcomeExit);
        TranslateAnimation logoExit = welcomeExit;
        logoExit.setDuration(800);
        getBinding().splashLogo.startAnimation(logoExit);

        // show fragment container
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getBinding().splashLogin.getLayoutParams();
        params.height = (int) disappearHeight;
        getBinding().splashLogin.setLayoutParams(params);

        TranslateAnimation loginEnter = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 0);
        loginEnter.setDuration(1200);
        loginEnter.setFillAfter(true);

        getBinding().splashLogin.startAnimation(loginEnter);
    }
}
