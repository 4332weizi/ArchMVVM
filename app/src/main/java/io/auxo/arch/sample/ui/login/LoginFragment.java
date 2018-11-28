package io.auxo.arch.sample.ui.login;

import android.arch.lifecycle.ViewModelProviders;

import io.auxo.arch.mvvm.view.fragment.MvvmFragment;
import io.auxo.arch.sample.R;
import io.auxo.arch.sample.databinding.FragmentLoginBinding;

/**
 * @author Victor Chiu
 */
public class LoginFragment extends MvvmFragment<FragmentLoginBinding> {

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void bindViewModels() {
        LoginViewModel viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        getBinding().setViewModel(viewModel);
    }

    @Override
    public void registerViewEvents() {
        getBinding().loginSubmit.setOnClickListener(v -> getBinding().getViewModel().login());
    }

    @Override
    public void subscribeViewModelChanges() {

    }
}
