package io.auxo.arch.mvvm.view;

import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

/**
 * MVVM中View层接口
 *
 * @param <VDB>
 * @author Victor Chiu
 */
public interface ViewOwner<VDB extends ViewDataBinding> {

    /**
     * 获取View层布局的layout res
     *
     * @return layout res
     */
    @LayoutRes
    int getContentLayoutId();

    /**
     * 通过DataBinding给 {@link #getContentLayoutId()}绑定ViewModel
     *
     * @param binding
     */
    void bindViewModels(@NonNull VDB binding);

    /**
     * 注册View中的操作交互事件
     * 将View的操作，反馈给ViewModel
     * 实现Command
     *
     * @param binding
     */
    void registerViewEvents(@NonNull VDB binding);

    /**
     * 订阅ViewModel中的数据变化
     * 实现Notifications
     *
     * @param binding
     */
    void subscribeViewModelChanges(@NonNull VDB binding);

    /**
     * 获取View的ViewDataBinding
     *
     * @return VDB
     */
    VDB getViewDataBinding();
}
