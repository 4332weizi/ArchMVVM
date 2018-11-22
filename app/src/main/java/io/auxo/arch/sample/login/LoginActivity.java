package io.auxo.arch.sample.login;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import io.auxo.arch.mvvm.view.activity.MvvmActivity;
import io.auxo.arch.sample.R;
import io.auxo.arch.sample.databinding.ActivityLoginBinding;

public class LoginActivity extends MvvmActivity<ActivityLoginBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBinding().loginToolbar.setTitle("Sign in to GitHub");
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void bindViewModels() {
        LoginViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        getBinding().setViewModel(viewModel);
    }

    @Override
    public void registerViewEvents() {

    }

    @Override
    public void subscribeViewModelChanges() {

    }
}
