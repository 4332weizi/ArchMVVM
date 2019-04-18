package io.auxo.arch.mvvm.viewmodel.livedata;

import androidx.lifecycle.MutableLiveData;

import io.auxo.arch.mvvm.utils.ThreadUtil;

/**
 * call setValue from outside MainThread
 *
 * @author Victor Chiu
 */
public class SafeMutableLiveData<T> extends MutableLiveData<T> {

    @Override
    public void setValue(T value) {
        if (ThreadUtil.isOnMainThread()) {
            super.setValue(value);
        } else {
            postValue(value);
        }
    }
}
