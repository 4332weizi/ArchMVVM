package io.auxo.arch.mvvm.view.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.auxo.arch.mvvm.view.ViewOwner;
import io.auxo.arch.mvvm.view.ViewOwnerHelper;

/**
 * @author Victor Chiu
 */
public abstract class MvvmFragment<VDB extends ViewDataBinding> extends BaseFragment
        implements ViewOwner<VDB> {

    protected VDB mViewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewOwnerHelper.onFragmentCreateView(this, inflater, this, container, false);
        return getBinding().getRoot();
    }

    @Override
    public void onBindingCreated(@NonNull VDB binding) {
        mViewDataBinding = binding;
    }

    @Override
    public VDB getBinding() {
        return mViewDataBinding;
    }
}
