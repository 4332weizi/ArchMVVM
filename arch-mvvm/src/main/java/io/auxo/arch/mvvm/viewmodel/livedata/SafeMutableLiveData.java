package io.auxo.arch.mvvm.viewmodel.livedata;

import android.arch.lifecycle.MutableLiveData;

import io.auxo.arch.mvvm.utils.ThreadUtil;

/**
 * @author Victor Chiu
 */

public class SafeMutableLiveData<T> extends MutableLiveData<T> {

    @Override
    public void setValue(T value) {
        if (ThreadUtil.isMainThread()) {
            super.setValue(value);
        } else {
            postValue(value);
        }
    }

}
