package io.auxo.arch.sample.ui.pullrequests;

import io.auxo.arch.mvvm.view.fragment.MvvmFragment;
import io.auxo.arch.sample.R;
import io.auxo.arch.sample.databinding.FragmentPullRequestsBinding;

/**
 * @author Victor Chiu
 */
public class PullRequestsFragment extends MvvmFragment<FragmentPullRequestsBinding> {
    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_pull_requests;
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
