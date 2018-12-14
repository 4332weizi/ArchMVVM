package io.auxo.arch.mvvm.viewmodel.livedata;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import io.auxo.arch.mvvm.utils.ThreadUtil;

/**
 * @author Victor Chiu
 */
public class SingleLiveEvent<T> extends LiveEvent<T> {

    private static final String TAG = "SingleLiveEvent";

    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @Override
    @MainThread
    public void observe(LifecycleOwner owner, final Observer<T> observer) {

        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }

        // Observe the internal MutableLiveData
        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@Nullable T t) {
                if (mPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    @Override
    @MainThread
    public void setValue(@Nullable T t) {
        if (ThreadUtil.isOnMainThread()) {
            mPending.set(true);
            super.setValue(t);
        } else {
            postValue(t);
        }
    }
}