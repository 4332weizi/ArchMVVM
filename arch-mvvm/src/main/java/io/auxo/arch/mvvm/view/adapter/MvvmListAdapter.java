package io.auxo.arch.mvvm.view.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.auxo.arch.mvvm.view.ViewOwner;
import io.auxo.arch.mvvm.view.ViewOwnerHelper;

public abstract class MvvmListAdapter<T> extends BaseListAdapter<T> {

    public MvvmListAdapter(List<T> items) {
        super(items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewOwner viewOwner;

        if (convertView == null) {
            viewOwner = createViewOwner(position, convertView, parent);
            convertView = viewOwner.getBinding().getRoot();
            convertView.setTag(viewOwner);
        } else {
            viewOwner = (ViewOwner) convertView.getTag();
        }

        ViewOwnerHelper.onCreate(viewOwner);

        return convertView;
    }

    public abstract ViewOwner createViewOwner(int position, View convertView, ViewGroup parent);
}
