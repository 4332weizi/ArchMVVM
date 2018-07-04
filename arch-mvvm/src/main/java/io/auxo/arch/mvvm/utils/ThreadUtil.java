package io.auxo.arch.mvvm.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * @author Victor Chiu
 */
public class ThreadUtil {

    public static boolean isMainThread() {
        return Thread.currentThread().equals(Looper.getMainLooper().getThread());
    }

    public static void runOnMainThread(Runnable runnable) {
        if (isMainThread()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }
}
