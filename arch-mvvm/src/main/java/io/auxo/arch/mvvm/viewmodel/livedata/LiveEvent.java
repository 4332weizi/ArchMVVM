package io.auxo.arch.mvvm.viewmodel.livedata;

/**
 * @author Victor Chiu
 */
public class LiveEvent<T> extends SafeMutableLiveData<T> {

    public void call() {
        call(null);
    }

    public void call(T value) {
        setValue(value);
    }
}
