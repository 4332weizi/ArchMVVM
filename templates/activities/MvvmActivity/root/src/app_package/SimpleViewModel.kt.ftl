package ${packageName};

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;

<#if layoutName?starts_with('activity')>
  <#assign bindingClassName="Activity${activityClass?replace('Activity$','','r')}Binding">
<#else>
  <#assign bindingClassName="${activityClass?replace('Activity$','','r')}Binding">
</#if>
import ${superClassFqcn};
import ${packageName}.databinding.${bindingClassName};

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
