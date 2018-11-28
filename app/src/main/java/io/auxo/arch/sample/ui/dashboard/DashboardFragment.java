package io.auxo.arch.sample.ui.dashboard;

import io.auxo.arch.mvvm.view.fragment.MvvmFragment;
import io.auxo.arch.sample.R;
import io.auxo.arch.sample.databinding.FragmentDashboardBinding;

/**
 * @author Victor Chiu
 */
public class DashboardFragment extends MvvmFragment<FragmentDashboardBinding> {

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_dashboard;
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
