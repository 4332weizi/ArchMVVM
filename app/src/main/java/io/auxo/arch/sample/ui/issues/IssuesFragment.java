package io.auxo.arch.sample.ui.issues;

import io.auxo.arch.mvvm.view.fragment.MvvmFragment;
import io.auxo.arch.sample.R;
import io.auxo.arch.sample.databinding.FragmentIssuesBinding;

/**
 * @author Victor Chiu
 */
public class IssuesFragment extends MvvmFragment<FragmentIssuesBinding> {

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_issues;
    }

    @Override
    public void bindViewModels() {

    }

    @Override
    public void registerViewEvents() {

    }

    @Override
    public void subscribeViewModelChanges() {

    }
}
