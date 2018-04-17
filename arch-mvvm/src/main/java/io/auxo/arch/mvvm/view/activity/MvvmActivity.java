package io.auxo.arch.mvvm.view.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import io.auxo.arch.mvvm.view.ViewOwner;

/**
 * @author Victor Chiu
 */
public abstract class MvvmActivity<VDB extends ViewDataBinding> extends BaseActivity implements ViewOwner<VDB> {

    private VDB mViewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, getContentLayoutId());
        // 绑定ViewModel
        bindViewModels(mViewDataBinding);
        // 注册View点击事件
        registerViewEvents(mViewDataBinding);
        // 订阅ViewModel中的数据变化
        subscribeViewModelChanges(mViewDataBinding);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyViewDataBinding(mViewDataBinding);
    }

    protected void destroyViewDataBinding(ViewDataBinding binding) {
        binding.unbind();
        binding.executePendingBindings();
    }

    public VDB getViewDataBinding() {
        return mViewDataBinding;
    }

}
