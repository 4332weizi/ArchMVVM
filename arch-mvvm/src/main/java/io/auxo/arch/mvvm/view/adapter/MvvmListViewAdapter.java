package io.auxo.arch.mvvm.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.auxo.arch.mvvm.view.ViewOwner;

public abstract class MvvmListViewAdapter<T> extends BaseListViewAdapter<T> {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding binding;
        ViewOwner viewOwner;
        if (convertView == null) {
            viewOwner = createViewHolder(position, convertView, parent);
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewOwner.getContentLayoutId(), parent, false);
            binding.getRoot().setTag(viewOwner);
        } else {
            binding = DataBindingUtil.getBinding(convertView);
            viewOwner = (ViewOwner) convertView.getTag();
        }
        viewOwner.bindViewModels(binding);
        viewOwner.registerViewEvents(binding);
        viewOwner.subscribeViewModelChanges(binding);
        return binding.getRoot();
    }

    protected abstract ViewOwner createViewHolder(int position, View convertView, ViewGroup parent);

}
