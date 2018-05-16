package io.auxo.arch.mvvm.view;

import android.support.annotation.NonNull;

public class ViewOwnerHelper {

    public static void onCreate(@NonNull ViewOwner owner) {
        // 绑定ViewModel
        owner.bindViewModels();
        // 注册View点击事件
        owner.registerViewEvents();
        // 订阅ViewModel中的数据变化
        owner.subscribeViewModelChanges();
    }

    public static void onDestroy(@NonNull ViewOwner owner) {
        owner.getBinding().unbind();
        owner.getBinding().executePendingBindings();
    }
}
