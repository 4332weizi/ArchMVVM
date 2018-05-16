package io.auxo.arch.mvvm.view.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.auxo.arch.mvvm.view.ViewOwner;
import io.auxo.arch.mvvm.view.ViewOwnerHelper;

/**
 * @author zhaoweiwei@qk365.com
 */
public abstract class MvvmFragment<VDB extends ViewDataBinding> extends BaseFragment implements ViewOwner<VDB> {

    protected VDB mViewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getContentLayoutId(), container, false);
        ViewOwnerHelper.onCreate(this);
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ViewOwnerHelper.onDestroy(this);
    }

    @Override
    public VDB getBinding() {
        return mViewDataBinding;
    }
}
