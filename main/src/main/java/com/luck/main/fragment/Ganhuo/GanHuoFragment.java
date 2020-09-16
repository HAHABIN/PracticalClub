/**
 * 文 件 名:  GanHuoFragment
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/9/8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.luck.main.fragment.Ganhuo;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.common.base.BaseFragment;
import com.example.common.bean.beanEnum.TypeEnum;
import com.luck.main.R;
import com.luck.main.R2;
import com.luck.main.adapter.TopNavAdapter;
import com.luck.main.adapter.Vp2Adapter;
import com.luck.main.fragment.main.ArticleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/9/8
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class GanHuoFragment extends BaseFragment implements TopNavAdapter.OnItemClickListener {

    @BindView(R2.id.rcv_top)
    RecyclerView rcvTop;
    @BindView(R2.id.vp2_fragment)
    ViewPager2 vp2Fragment;

    private Vp2Adapter mVpAdapter;

    private TopNavAdapter mTopNavAdapter;
    private List<Fragment> mFragmentList;

    private ArrayList<String> mTitles;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ganhuo;
    }

    @Override
    protected void initView(View view) {
        initFragmentList();
        mTopNavAdapter = new TopNavAdapter(getContext(),mTitles,this);
        rcvTop.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvTop.setAdapter(mTopNavAdapter);
        mVpAdapter = new Vp2Adapter(this,mFragmentList);
        vp2Fragment.setAdapter(mVpAdapter);
    }

    private void initFragmentList() {

        mTitles = new ArrayList<>();
        mTitles.add(TypeEnum.All.getName());
        mTitles.add(TypeEnum.ANDROID.getName());
        mTitles.add(TypeEnum.Flutter.getName());
        mTitles.add(TypeEnum.IOS.getName());
        mTitles.add(TypeEnum.app.getName());
        mTitles.add(TypeEnum.frontend.getName());
        mTitles.add(TypeEnum.backend.getName());

        mFragmentList = new ArrayList<>();
        mFragmentList.add(ArticleFragment.newInstance(1, TypeEnum.All.getType()));
        mFragmentList.add(ArticleFragment.newInstance(1, TypeEnum.ANDROID.getType()));
        mFragmentList.add(ArticleFragment.newInstance(1, TypeEnum.Flutter.getType()));
        mFragmentList.add(ArticleFragment.newInstance(1, TypeEnum.IOS.getType()));
        mFragmentList.add(ArticleFragment.newInstance(1, TypeEnum.app.getType()));
        mFragmentList.add(ArticleFragment.newInstance(1, TypeEnum.frontend.getType()));
        mFragmentList.add(ArticleFragment.newInstance(1, TypeEnum.backend.getType()));

    }


    @Override
    protected void initListener() {
        vp2Fragment.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mTopNavAdapter.setStaus(position);
                //定位当前位置
                rcvTop.scrollToPosition(position);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onItemClick(int position) {
        //顶部导航栏点击回调
        vp2Fragment.setCurrentItem(position);
    }
}
