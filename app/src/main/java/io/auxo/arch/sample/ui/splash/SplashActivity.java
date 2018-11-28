package io.auxo.arch.sample.ui.splash;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import io.auxo.arch.mvvm.view.activity.MvvmActivity;
import io.auxo.arch.sample.R;
import io.auxo.arch.sample.databinding.ActivitySplashBinding;
import io.auxo.arch.sample.ui.main.MainActivity;
import io.auxo.arch.sample.ui.login.LoginFragment;
import io.auxo.arch.sample.ui.login.LoginViewModel;

/**
 * @author Victor Chiu
 */
public class SplashActivity extends MvvmActivity<ActivitySplashBinding> {

    private LoginFragment mLoginFragment;
    private LoginViewModel mLoginViewModel;

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void onBindingCreated(@NonNull ActivitySplashBinding binding) {
        super.onBindingCreated(binding);
        addLoginFragment();
    }

    @Override
    public void bindViewModels() {
        SplashViewModel viewModel = ViewModelProviders.of(this)
                .get(SplashViewModel.class);
        getBinding().setViewModel(viewModel);
    }

    @Override
    public void registerViewEvents() {

    }

    @Override
    public void subscribeViewModelChanges() {
        getBinding().getViewModel().getNavigateMainEvent()
                .observe(this, nul1 -> navigateMain());
        getBinding().getViewModel().getNavigateLoginEvent()
                .observe(this, nul1 -> showLoginFragment());
    }

    private void addLoginFragment() {
        // add LoginFragment
        mLoginFragment = new LoginFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.splash_login, mLoginFragment)
                .hide(mLoginFragment)
                .commit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof LoginFragment) {
            mLoginViewModel = ViewModelProviders.of(mLoginFragment)
                    .get(LoginViewModel.class);
            mLoginViewModel.getLoginSuccessEvent()
                    .observe(this, nul1 -> navigateMain());
        }
    }

    private void navigateMain() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void showLoginFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .show(mLoginFragment)
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
