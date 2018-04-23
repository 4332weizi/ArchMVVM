package ${packageName};

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

<#if generateLayout>
    <#assign bindingClassName="${layoutName?replace('_',' ')?capitalize?replace(' ','')}Binding">
<#else>
import android.databinding.ViewDataBinding;
  <#assign bindingClassName="ViewDataBinding">
</#if>
import ${superClassFqcn};
<#if applicationPackage??>
import ${applicationPackage}.R;
import ${applicationPackage}.databinding.${bindingClassName};
<#else>
import ${packageName}.R;
import ${packageName}.databinding.${bindingClassName};
</#if>

public class ${activityClass} extends ${superClass}<${bindingClassName}>{

    @Override
    public int getContentLayoutId() {
<#if generateLayout>
    return R.layout.${layoutName};
<#else>
    return 0;
</#if>
    }

    @Override
    public void bindViewModels(@NonNull ${bindingClassName} binding) {
<#if generateViewModel>
        ${viewModelName} viewModel = ViewModelProviders.of(this).get(${viewModelName}.class);
        binding.setViewModel(viewModel);
</#if>
    }

    @Override
    public void registerViewEvents(@NonNull ${bindingClassName} binding) {

    }

    @Override
    public void subscribeViewModelChanges() {

    }
}
