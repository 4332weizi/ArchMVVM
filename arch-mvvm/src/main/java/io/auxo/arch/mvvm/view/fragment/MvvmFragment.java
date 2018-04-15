package io.auxo.arch.mvvm.view.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author zhaoweiwei@qk365.com
 */
public abstract class MvvmFragment<VDB extends ViewDataBinding> extends BaseFragment {

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
        subscribeViewModelChanges();
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

    /**
     * 获取View层布局的layout res
     *
     * @return layout res
     */
    @LayoutRes
    protected abstract int getContentLayoutId();

    /**
     * 通过DataBinding给 {@link #getContentLayoutId()}绑定ViewModel
     *
     * @param binding
     */
    protected abstract void bindViewModels(@NonNull VDB binding);

    /**
     * 注册View中的操作交互事件
     * 将View的操作，反馈给ViewModel
     * 实现Command
     *
     * @param binding
     */
    protected abstract void registerViewEvents(@NonNull VDB binding);

    /**
     * 订阅ViewModel中的数据变化
     * 实现Notifications
     */
    protected abstract void subscribeViewModelChanges();

    protected VDB getViewDataBinding() {
        return mViewDataBinding;
    }
}
