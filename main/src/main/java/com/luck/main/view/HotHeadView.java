/**
 * 文 件 名:  HotHeadView
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/9/1
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.luck.main.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.common.bean.entity.BannerEntity;
import com.example.common.utils.Utils;
import com.luck.main.R;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * <推进头部> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/9/1
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HotHeadView extends LinearLayout {

    private HotHeaderViewListener hotHeaderViewListener;

    public interface HotHeaderViewListener {
        void openBanner(BannerEntity.ResultBean item);
    }

    public void setHotHeaderViewListener(HotHeaderViewListener hotHeaderViewListener) {
        this.hotHeaderViewListener = hotHeaderViewListener;
    }

    private Context mContext;

    private TextView mTvBannerName;

    private XBanner mBanner;

    private ImageView mIvBottom;

    private ArrayList<BannerEntity.ResultBean> mBannerList;

    public HotHeadView(Context context) {
        super(context);
        initView(context);
    }

    public HotHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    public void startTurning() {
        mBanner.startAutoPlay();
    }

    public void stopTurning() {
        mBanner.stopAutoPlay();
    }

    private void initView(Context context) {
        this.mContext = context;
        View view = ViewGroup.inflate(context, R.layout.view_head_hot, this);
        mIvBottom = view.findViewById(R.id.iv_banner_info_bottom);
        setBanner(view);
    }

    private void setBanner(View view) {
        mTvBannerName = view.findViewById(R.id.tv_info_banner_name);
        mBanner = view.findViewById(R.id.banner_info);
        mIvBottom = view.findViewById(R.id.iv_banner_info_bottom);
        //设置广告图片点击事件
        mBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                if (hotHeaderViewListener != null) {
                    hotHeaderViewListener.openBanner(mBannerList.get(position));
                }
            }
        });
        //切换
        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                BannerEntity.ResultBean bannerBean = mBannerList.get(i);
                mTvBannerName.setText(bannerBean.getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //加载广告图片
        mBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Utils.Glideload(mBannerList.get(position).getImage(),view.findViewById(R.id.ivPost));
            }
        });
    }


    public void setBannerList(ArrayList<BannerEntity.ResultBean> banners) {

        if (Utils.isNotEmpty(banners)) {
            mBanner.setVisibility(VISIBLE);
            mTvBannerName.setVisibility(VISIBLE);
            mIvBottom.setVisibility(VISIBLE);
            if (mBannerList==null) {
                mBannerList = new ArrayList<>();
            } else {

            }
            this.mBannerList.clear();
            this.mBannerList.addAll(banners);
            //当只有一个时 就重复三个
            if (banners.size() == 1) {
                this.mBannerList.add(banners.get(0));
                this.mBannerList.add(banners.get(0));
            }
            //同上
            if (banners.size() == 2) {
                this.mBannerList.addAll(banners);
            }
            //开启一屏显示多个模式
            mBanner.setIsClipChildrenMode(true);
            mBanner.setBannerData(R.layout.view_hot_banner, this.mBannerList);
            mBanner.setAutoPlayAble(this.mBannerList.size() > 1);
        } else {
            mBanner.setVisibility(INVISIBLE);
            mTvBannerName.setVisibility(INVISIBLE);
            mIvBottom.setVisibility(INVISIBLE);
        }
    }
}
