package io.auxo.arch.mvvm.view.adapter;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.auxo.arch.mvvm.view.ViewOwner;
import io.auxo.arch.mvvm.view.ViewOwnerHelper;

/**
 * @author Victor Chiu
 */
public abstract class MvvmListAdapter<T> extends BaseListAdapter<T> {

    public MvvmListAdapter(List<T> items) {
        super(items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemViewOwner itemViewOwner;

        if (convertView == null) {
            itemViewOwner = onCreateViewOwner(parent, getItem(position), position);
            ViewOwnerHelper.onCreateViewDataBinding(LayoutInflater.from(parent.getContext()), itemViewOwner, parent, false);
        } else {
            itemViewOwner = (ItemViewOwner) convertView.getTag();
        }

        ViewOwnerHelper.onBindingCreated(itemViewOwner);

        return itemViewOwner.getBinding().getRoot();
    }

    /**
     * 创建ViewOwner
     *
     * @param parent
     * @param item
     * @param position
     * @return
     */
    protected abstract ItemViewOwner onCreateViewOwner(ViewGroup parent, T item, int position);

    public abstract class ItemViewOwner<VDB extends ViewDataBinding> implements ViewOwner<VDB> {

        private VDB mViewDataBinding;

        @Override
        public void bindViewModels() {
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
}