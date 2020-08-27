package com.example.common.base.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class VpAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentList;
    private List<String> mTitleList;

    public VpAdapter(@NonNull FragmentManager fm, List<Fragment> mFragmentList, List<String> mTitleList) {
        // FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT 懒加载
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mFragmentList = mFragmentList;
        this.mTitleList = mTitleList;
    }

    public VpAdapter(@NonNull FragmentManager fm, List<Fragment> mFragmentList) {
        super(fm,  BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList == null ? "":mTitleList.get(position);
    }
}
