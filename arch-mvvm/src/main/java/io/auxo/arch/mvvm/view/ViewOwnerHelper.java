package io.auxo.arch.mvvm.view;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author Victor Chiu
 */
public class ViewOwnerHelper {

    public static <VDB extends ViewDataBinding> VDB onActivityCreate(@NonNull Activity activity, @NonNull ViewOwner owner) {
        VDB binding = DataBindingUtil.setContentView(activity, owner.getContentLayoutId());
        owner.onBindingCreated(binding);
        return binding;
    }

    public static <VDB extends ViewDataBinding> VDB onFragmentCreateView(@NonNull LayoutInflater inflater, @NonNull ViewOwner owner, @Nullable ViewGroup container, boolean attachToRoot) {
        VDB binding = DataBindingUtil.inflate(inflater, owner.getContentLayoutId(), container, attachToRoot);
        owner.onBindingCreated(binding);
        return binding;
    }

    public static <VDB extends ViewDataBinding> VDB onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewOwner owner, @Nullable ViewGroup container, boolean attachToRoot) {
        VDB binding = DataBindingUtil.inflate(inflater, owner.getContentLayoutId(), container, attachToRoot);
        owner.onBindingCreated(binding);
        return binding;
    }

    public static void onBind(ViewOwner owner) {
        // 绑定ViewModel
        owner.bindViewModels();
        // 注册View点击事件
        owner.registerViewEvents();
        // 订阅ViewModel中的数据变化
        owner.subscribeViewModelChanges();
        owner.getBinding().executePendingBindings();
    }

    public static void onViewDestroy(ViewOwner owner) {
        owner.getBinding().unbind();
        owner.getBinding().executePendingBindings();
    }
}
