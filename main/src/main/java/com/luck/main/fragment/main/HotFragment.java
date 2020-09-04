package com.luck.main.fragment.main;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.common.base.BaseFragment;
import com.example.common.base.SwipeRecyclerFragment;
import com.example.common.base.WebActivity;
import com.example.common.base.adapter.CommonAdapter;
import com.example.common.base.mvp.BaseContract;
import com.example.common.bean.HttpItem;
import com.example.common.bean.beanEnum.CategoryEnum;
import com.example.common.bean.beanEnum.HotEnum;
import com.example.common.bean.entity.BannerEntity;
import com.example.common.bean.entity.CategoryEntity;
import com.example.common.bean.entity.HotEntity;
import com.example.common.http.ApiError;
import com.example.common.http.HttpHelper;
import com.example.common.widget.view.ListEmptyView;
import com.luck.main.R;
import com.luck.main.adapter.GirlAdapter;
import com.luck.main.adapter.NewsAdapter;
import com.luck.main.contract.HotContract;
import com.luck.main.presenter.HotPresenter;
import com.luck.main.view.HotHeadView;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/25
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HotFragment extends SwipeRecyclerFragment<HotContract.Presenter> implements HotContract.View {

    private HotHeadView mHotHeadView;

    private CategoryEnum categoryEnum = CategoryEnum.Acticle;
    @Override
    protected View setHeaderView() {
        mHotHeadView = new HotHeadView(mActivity);
        return mHotHeadView;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mPresenter.getBanner();
    }

    @Override
    protected View setEmptyView() {
        return new ListEmptyView(getContext());
    }

    @Override
    protected void initListener() {
        super.initListener();
        mHotHeadView.setHotHeaderViewListener(new HotHeadView.HotHeaderViewListener() {
            @Override
            public void openBanner(BannerEntity.ResultBean item) {
                WebActivity.startActivity(getContext(),item.getUrl());
            }
        });
        startRefresh();
        addLoadMoreView();
    }

    @Override
    protected void setLayoutManager() {
        super.setLayoutManager();
    }

    @Override
    public CommonAdapter getAdapter() {
        return new NewsAdapter(getContext());
    }

    @Override
    protected HotContract.Presenter bindPresenter() {
        return new HotPresenter();
    }


    @Override
    protected void RequestData() {
        showPleaseDialog();
        mPresenter.getHotData(HotEnum.views, categoryEnum);
    }


    @Override
    public void taskStarted(HttpHelper.TaskType type) {

    }

    @Override
    public void onSuccess(HttpHelper.TaskType type, HttpItem item) {
        dismissDialog();
        stopLoad();
        switch (type) {
            case Banners:
                if (item instanceof BannerEntity) {
                    ArrayList<BannerEntity.ResultBean> data = ((BannerEntity) item).getData();
                    mHotHeadView.setBannerList(data);
                }
                break;
            case Hot:
                if (item instanceof HotEntity) {
                    ArrayList<CategoryEntity.ResultBean> data = ((HotEntity) item).getData();
                    mAdapter.setData(data);
                    mAdapter.notifyDataSetChanged();
                    swipeRecyclerView.noMoreData();
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mHotHeadView.startTurning();
    }

    @Override
    public void onPause() {
        super.onPause();
        mHotHeadView.stopTurning();
    }

    @Override
    public void onSuccess(HttpHelper.TaskType type, JSONObject object) {

    }

    @Override
    public void onFailure(HttpHelper.TaskType type, ApiError e) {
        Toast.makeText(mActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
