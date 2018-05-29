package io.auxo.arch.mvvm.utils;

import android.os.Looper;

/**
 * @author Victor Chiu
 */
public class ThreadUtil {

    public static boolean isMainThread() {
        return Thread.currentThread().equals(Looper.getMainLooper().getThread());
    }
}
