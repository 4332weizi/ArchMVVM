package io.auxo.arch.sample.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.auxo.arch.mvvm.view.activity.MvvmActivity;
import io.auxo.arch.sample.R;
import io.auxo.arch.sample.UserManager;
import io.auxo.arch.sample.databinding.ActivityMainBinding;
import io.auxo.arch.sample.databinding.NavigationHeaderBinding;
import io.auxo.arch.sample.ui.dashboard.DashboardFragment;
import io.auxo.arch.sample.ui.issues.IssuesFragment;
import io.auxo.arch.sample.ui.pullrequests.PullRequestsFragment;

/**
 * @author Victor Chiu
 */
public class MainActivity extends MvvmActivity<ActivityMainBinding> {

    private List<Fragment> mFragments = new ArrayList<>();
    private List<CharSequence> mPageTitles = new ArrayList<>();

    private FragmentPagerAdapter mPagerAdapter;

    private NavigationHeaderBinding mNavigationBinding;

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBindingCreated(@NonNull ActivityMainBinding binding) {
        super.onBindingCreated(binding);
        initFragments();
        initNavigationViewHeader();
        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), mFragments, mPageTitles);
        binding.mainNavPager.setAdapter(mPagerAdapter);
        // bind tabs to viewpager
        binding.mainNavMenu.setupWithViewPager(binding.mainNavPager);
    }

    @Override
    public void bindViewModels() {

    }

    @Override
    public void registerViewEvents() {
        getBinding().navView.setNavigationItemSelectedListener(
                menuItem -> {
                    menuItem.setCheckable(true);//设置选项可选
                    menuItem.setChecked(true);//设置选型被选中
                    getBinding().drawerLayout.closeDrawers();//关闭侧边菜单栏
                    return true;
                });
    }

    @Override
    public void subscribeViewModelChanges() {

    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mPageTitles = new ArrayList<>();
        Collections.addAll(mPageTitles, "Dashboard", "Pull requests", "Issues");
        Collections.addAll(mFragments, new DashboardFragment(), new PullRequestsFragment(),
                new IssuesFragment());
    }

    private void initNavigationViewHeader() {
        mNavigationBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
                R.layout.navigation_header, null, false);
        mNavigationBinding.setViewModel(UserManager.get().getUser());
        getBinding().navView.addHeaderView(mNavigationBinding.getRoot());
    }
}
