package com.luck.main.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <ViewPager2适配器> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/9/8
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Vp2Adapter extends FragmentStateAdapter {

    private List<Fragment> mFragmentList;

    public Vp2Adapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragmentList) {
        super(fragmentActivity);
        this.mFragmentList = fragmentList;
    }

    public Vp2Adapter(@NonNull Fragment fragment, List<Fragment> fragmentList) {
        super(fragment);
        this.mFragmentList = fragmentList;
    }

    public Vp2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Fragment> fragmentList) {
        super(fragmentManager, lifecycle);
        this.mFragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }
}
