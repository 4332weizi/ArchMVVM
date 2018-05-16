package ${packageName};

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

<#if includeLayout>
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

public class ${className} extends ${superClass}<${bindingClassName}> {

    public ${className}() {
        // Required empty public constructor
    }

    @Override
    public int getContentLayoutId() {
<#if includeLayout>
    return R.layout.${fragmentName};
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
