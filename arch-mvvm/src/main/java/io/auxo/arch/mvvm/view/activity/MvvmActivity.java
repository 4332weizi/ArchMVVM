package io.auxo.arch.mvvm.view.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;

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
        ViewOwnerHelper.onActivityCreate(this, this);
    }

    @Override
    public void onBindingCreated(@NonNull VDB binding) {
        mViewDataBinding = binding;
        ViewOwnerHelper.onBind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewOwnerHelper.onViewDestroy(this);
    }


    public VDB getBinding() {
        return mViewDataBinding;
    }

}
