package io.auxo.arch.mvvm.view.adapter;

import android.support.annotation.NonNull;
import android.widget.BaseAdapter;

import java.util.Collection;
import java.util.List;

public abstract class BaseListAdapter<T> extends BaseAdapter {

    private List<T> items;

    public BaseListAdapter(List<T> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public boolean add(T t) {
        return notifyDataSetChanged(items.add(t));
    }

    public boolean remove(Object o) {
        return notifyDataSetChanged(items.remove(o));
    }

    public boolean containsAll(@NonNull Collection<?> c) {
        return notifyDataSetChanged(items.containsAll(c));
    }

    public boolean addAll(@NonNull Collection<? extends T> c) {
        return notifyDataSetChanged(items.addAll(c));
    }

    public boolean addAll(int index, @NonNull Collection<? extends T> c) {
        return notifyDataSetChanged(items.addAll(index, c));
    }

    public boolean removeAll(@NonNull Collection<?> c) {
        return notifyDataSetChanged(items.removeAll(c));
    }

    public boolean retainAll(@NonNull Collection<?> c) {
        return notifyDataSetChanged(items.retainAll(c));
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public T set(int index, T element) {
        T preElement = items.set(index, element);
        notifyDataSetChanged();
        return preElement;
    }

    public void add(int index, T element) {
        items.add(index, element);
        notifyDataSetChanged();
    }

    public T remove(int index) {
        T element = items.remove(index);
        notifyDataSetChanged();
        return element;
    }

    protected boolean notifyDataSetChanged(boolean should) {
        if (should) {
            notifyDataSetChanged();
        }
        return should;
    }

}
