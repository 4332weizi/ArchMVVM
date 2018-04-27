package io.auxo.arch.mvvm.view.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import io.auxo.arch.mvvm.view.ViewOwner;
import io.auxo.arch.mvvm.view.ViewOwnerHelper;

/**
 * @author Victor Chiu
 */
public abstract class MvvmActivity<VDB extends ViewDataBinding> extends BaseActivity implements ViewOwner<VDB> {

    protected VDB mViewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, getContentLayoutId());
        // 绑定ViewModel
        bindViewModels();
        // 注册View点击事件
        registerViewEvents();
        // 订阅ViewModel中的数据变化
        subscribeViewModelChanges();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewOwnerHelper.onDestroy(this);
    }

    public VDB getBinding() {
        return mViewDataBinding;
    }

}
