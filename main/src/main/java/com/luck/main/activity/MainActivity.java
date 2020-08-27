package com.luck.main.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.common.base.BaseActivity;
import com.example.common.base.adapter.VpAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.luck.main.R;
import com.luck.main.R2;
import com.luck.main.fragment.main.GirlFragment;
import com.luck.main.fragment.main.HotFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {


    @BindView(R2.id.vp_content)
    ViewPager mViewPager;
    @BindView(R2.id.bottom_nav)
    BottomNavigationView mBottomNav;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;


    private List<Fragment> mFragmentList;
    private VpAdapter mVpAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new HotFragment());
        mFragmentList.add(new GirlFragment());
        mFragmentList.add(new HotFragment());
        mFragmentList.add(new HotFragment());
        mVpAdapter = new VpAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mVpAdapter);
        mViewPager.setCurrentItem(0);
    }

    @Override
    protected void initListener() {
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int menuId = menuItem.getItemId();
                // 跳转指定页面：Fragment
                if (menuId == R.id.navigation_hot) {
                    mViewPager.setCurrentItem(0);
                } else if (menuId == R.id.navigation_girl) {
                    mViewPager.setCurrentItem(1);
                } else if (menuId == R.id.navigation_article) {
                    mViewPager.setCurrentItem(2);
                } else if (menuId == R.id.navigation_real_stuff) {
                    mViewPager.setCurrentItem(3);
                }
                return false;

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //将滑动到的页面对应的 menu 设置为选中状态
                mBottomNav.getMenu().getItem(position).setChecked(true);
                switch (position) {
                    case 0:
                        mTvTitle.setText(getString(R.string.title_hot));
                        break;
                    case 1:
                        mTvTitle.setText(getString(R.string.title_girl));
                        break;
                    case 2:
                        mTvTitle.setText(getString(R.string.title_article));
                        break;
                    case 3:
                        mTvTitle.setText(getString(R.string.title_real_stuff));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {

    }


}