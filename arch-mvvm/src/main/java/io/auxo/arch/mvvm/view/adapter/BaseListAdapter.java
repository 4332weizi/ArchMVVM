package io.auxo.arch.mvvm.view.adapter;

import android.widget.BaseAdapter;

import java.util.List;

public abstract class BaseListAdapter<T> extends BaseAdapter {

    private List<T> mData;

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
