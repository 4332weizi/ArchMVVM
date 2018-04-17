package io.auxo.arch.mvvm.view.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.auxo.arch.mvvm.view.ViewOwner;

/**
 * @author zhaoweiwei@qk365.com
 */
public abstract class MvvmFragment<VDB extends ViewDataBinding> extends BaseFragment implements ViewOwner<VDB> {

    private VDB mViewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getContentLayoutId(), container, false);
        bindViewModels(mViewDataBinding);
        // 绑定ViewModel
        bindViewModels(mViewDataBinding);
        // 注册View点击事件
        registerViewEvents(mViewDataBinding);
        // 订阅ViewModel中的数据变化
        subscribeViewModelChanges(mViewDataBinding);
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyViewDataBinding(mViewDataBinding);
    }

    protected void destroyViewDataBinding(ViewDataBinding binding) {
        binding.unbind();
        binding.executePendingBindings();
    }

    @Override
    public VDB getViewDataBinding() {
        return mViewDataBinding;
    }
}
