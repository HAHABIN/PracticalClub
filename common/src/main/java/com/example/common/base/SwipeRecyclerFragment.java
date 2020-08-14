/**
 * 文 件 名:  SwipeRecyclerFragment
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/8/14
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.example.common.base;

import android.view.View;

import com.example.common.R;
import com.example.common.base.adapter.CommonAdapter;
import com.example.common.base.mvp.BaseContract;
import com.example.common.widget.view.ListEmptyView;
import com.example.common.widget.view.SwipeRecyclerView;

import java.util.ArrayList;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/14
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class SwipeRecyclerFragment<T extends BaseContract.BasePresenter> extends BaseFragment {

    protected SwipeRecyclerView swipeRecyclerView;

    protected ListEmptyView listEmptyView;

    protected ArrayList mDataList;

    protected T mPresenter;

    /**当前页*/
    protected int mStart = 1;
    /**每页请求数*/
    protected int mSize = 10;
    /**空白页状态 0 为搜索空白页  1 为刷新空白页 2 无网络空白页*/
    protected int mSearchType = 1;

    protected CommonAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.base_refresh;
    }
    /** 绑定*/
    @Override
    protected void processLogic(){
        mPresenter = bindPresenter();
        mPresenter.attachView(this);
    }
    @Override
    protected void initView(View view) {
        swipeRecyclerView = view.findViewById(R.id.swipe_rec);
        mAdapter = getAdapter();
        swipeRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        swipeRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {
                mStart = 1;
                RequestData();
            }

            @Override
            public void onLoadMore() {
                mStart++;
                RequestData();
            }
        });
    }

    @Override
    protected void initData() {

    }

    /**
     * 设置适配器
     */
    public abstract CommonAdapter getAdapter();
    /**
     * 设置空白界面
     * 不设置返回null
     *
     * @return
     */
    public void setEmptyView() {
        if (listEmptyView == null) {
            listEmptyView = new ListEmptyView(getContext());
            swipeRecyclerView.setEmptyView(listEmptyView);
        }
    }

    /**
     * 设置头部界面
     * 不设置返回null
     *
     * @return
     */
    public void setHeaderView(View view) {
        if (view !=null) {
            swipeRecyclerView.addHeaderView(view);
        }
    }

    /** 绑定Presenter*/
    protected abstract T bindPresenter();
    /**加载数据*/
    protected abstract void RequestData();

    protected void startRefresh() {
        swipeRecyclerView.setRefreshing(true);
    }

    protected void noMoreData() {
        swipeRecyclerView.noMoreData();
    }

    protected void loadMoreData(){
        swipeRecyclerView.loadMoreData();
    }

    protected void addLoadMoreView() {
        swipeRecyclerView.addLoadMoreView();
    }

    protected void stopLoad() {
        swipeRecyclerView.stopLoad();
    }

    /**
     * @param data 列表数据
     * mSearchType 空白页状态 0 为搜索空白页  1 为刷新空白页 2 无网络空白页
     */
    protected void setAdapterData(ArrayList data) {
        stopLoad();
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        if (mStart == 1) {
            mDataList.clear();
        }
        /** 设置空白页*/
        setEmptyView();
        //判断数据是否为空
        if (data != null && !data.isEmpty()) {
            mDataList.addAll(data);
            if (data.size() < mSize) {
                noMoreData();
            }
        } else {
            if (mSearchType == 0) {
                listEmptyView.setNoSearchView();
            } else if (mSearchType == 1){
                listEmptyView.setNoDataView();
            } else {
                listEmptyView.setNoNetWorkView();
            }
        }

        mAdapter.setData(mDataList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
