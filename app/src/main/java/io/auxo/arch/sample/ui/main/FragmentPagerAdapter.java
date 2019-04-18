package io.auxo.arch.sample.ui.main;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.List;

/**
 * @author Victor Chiu
 */
public class FragmentPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private List<CharSequence> mPageTitles;

    public FragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<CharSequence> pageTitles) {
        super(fm);
        mFragments = fragments;
        mPageTitles = pageTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitles == null || mPageTitles.size() <= position ? "" : mPageTitles.get(position);
    }
}
