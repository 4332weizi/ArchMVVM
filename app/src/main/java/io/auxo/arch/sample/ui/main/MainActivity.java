package io.auxo.arch.sample.ui.main;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.auxo.arch.mvvm.view.activity.MvvmActivity;
import io.auxo.arch.sample.R;
import io.auxo.arch.sample.databinding.ActivityMainBinding;
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

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBindingCreated(@NonNull ActivityMainBinding binding) {
        super.onBindingCreated(binding);
        initFragments();
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
}
