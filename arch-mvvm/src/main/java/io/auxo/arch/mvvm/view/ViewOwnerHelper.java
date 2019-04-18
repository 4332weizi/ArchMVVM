package io.auxo.arch.mvvm.view;

import android.app.Activity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.auxo.arch.mvvm.utils.Objects;

/**
 * @author Victor Chiu
 */
public class ViewOwnerHelper {

    public static <VDB extends ViewDataBinding> VDB onActivityCreate(
            @NonNull Activity activity, @NonNull ViewOwner owner) {
        checkViewOwnerNonNull(owner);
        Objects.requireNonNull(activity, "activity must not be null");
        VDB binding = DataBindingUtil.setContentView(activity, owner.getContentLayoutId());
        dispatchBindingCreated(owner, binding);
        if (activity instanceof LifecycleOwner) {
            ((LifecycleOwner) activity).getLifecycle().addObserver(new ViewLifecycleObserver(owner));
        } else {
            ViewLifecycleFragment.injectIfNeededIn(activity, owner);
        }
        return binding;
    }

    public static <VDB extends ViewDataBinding> VDB onFragmentCreateView(
            @NonNull Fragment fragment, @NonNull LayoutInflater inflater,
            @NonNull ViewOwner owner, @Nullable ViewGroup container, boolean attachToRoot) {
        VDB binding = onCreateViewDataBinding(inflater, owner, container, attachToRoot);
        Objects.requireNonNull(fragment, "fragment must not be null");
        fragment.getLifecycle().addObserver(new ViewLifecycleObserver(owner));
        return binding;
    }

    public static <VDB extends ViewDataBinding> VDB onCreateViewDataBinding(
            @NonNull LayoutInflater inflater, @NonNull ViewOwner owner,
            @Nullable ViewGroup container, boolean attachToRoot) {
        checkViewOwnerNonNull(owner);
        VDB binding = DataBindingUtil.inflate(inflater, owner.getContentLayoutId(), container, attachToRoot);
        dispatchBindingCreated(owner, binding);
        return binding;
    }

    protected static <VDB extends ViewDataBinding> void dispatchBindingCreated(ViewOwner owner, VDB binding) {
        owner.onBindingCreated(binding);
        onBindingCreated(owner);
    }

    public static void onBindingCreated(ViewOwner owner) {
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

    public static void checkViewOwnerNonNull(ViewOwner owner) {
        Objects.requireNonNull(owner, "ViewOwner must not be null");
    }

    static class ViewLifecycleObserver implements DefaultLifecycleObserver {

        private ViewOwner owner;

        public ViewLifecycleObserver(ViewOwner owner) {
            this.owner = owner;
        }

        @Override
        public void onDestroy(@NonNull LifecycleOwner owner) {
            onViewDestroy(this.owner);
        }
    }

    public static class ViewLifecycleFragment extends android.app.Fragment {

        private static final String VIEW_LIFECYCLE_FRAGMENT_TAG = "io.auxo.arch.mvvm.view"
                + "ViewOwnerHelper.ViewLifecycleFragment.view_lifecycle_fragment_tag";

        private ViewOwner owner;

        public void setViewOwner(ViewOwner owner) {
            this.owner = owner;
        }

        public static void injectIfNeededIn(Activity activity, ViewOwner owner) {
            android.app.FragmentManager manager = activity.getFragmentManager();
            if (manager.findFragmentByTag(VIEW_LIFECYCLE_FRAGMENT_TAG) == null) {
                ViewLifecycleFragment fragment = new ViewLifecycleFragment();
                fragment.setViewOwner(owner);
                manager.beginTransaction().add(fragment, VIEW_LIFECYCLE_FRAGMENT_TAG).commit();
                manager.executePendingTransactions();
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            onViewDestroy(owner);
        }
    }
}
