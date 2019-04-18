package io.auxo.arch.sample.ui.login;

import androidx.lifecycle.ViewModelProviders;
import android.widget.Toast;

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

    }

    @Override
    public void subscribeViewModelChanges() {
        getBinding().getViewModel().message.observe(this, message -> {
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        });
    }
}
