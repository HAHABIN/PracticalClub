package com.example.common.base;

import android.view.View;

import com.example.common.R;
import com.example.common.base.adapter.CommonAdapter;
import com.example.common.base.mvp.BaseContract;
import com.example.common.utils.Utils;
import com.example.common.widget.view.ListEmptyView;
import com.example.common.widget.view.SwipeRecyclerView;

import java.util.ArrayList;

/**
 * <MVP通用刷新Activity> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/13
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class SwipeRecyclerActivity<T extends BaseContract.BasePresenter> extends NavbarActivity {


    protected SwipeRecyclerView swipeRecyclerView;

    protected ListEmptyView listEmptyView;

    protected ArrayList mDataList;

    protected T mPresenter;

    protected abstract T bindPresenter();

    /**
     * 绑定
     */
    @Override
    protected void processLogic() {
        mPresenter = bindPresenter();
        mPresenter.attachView(this);
    }


    /**
     * 当前页
     */
    protected int mStart = 1;
    /**
     * 每页请求数
     */
    protected int mSize = 10;

    /**
     * 空白页状态 0 为搜索空白页  1 为刷新空白页 2 无网络空白页
     */
    protected int mSearchType = 1;

    protected CommonAdapter mAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.base_refresh;
    }


    @Override
    protected void initParam() {
        super.initParam();
    }

    @Override
    protected void initView() {
        swipeRecyclerView = findViewById(R.id.swipe_rec);
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
            listEmptyView = new ListEmptyView(mContext);
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
        if (view != null) {
            swipeRecyclerView.addHeaderView(view);
        }
    }


    /**
     * 加载数据
     */
    protected abstract void RequestData();

    protected void startRefresh() {
        swipeRecyclerView.setRefreshing(true);
    }

    protected void noMoreData() {
        swipeRecyclerView.noMoreData();
    }

    protected void loadMoreData() {
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
     *             mSearchType 空白页状态 0 为搜索空白页  1 为刷新空白页 2 无网络空白页
     */
    protected void setAdapterData(ArrayList data) {
        stopLoad();
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        //当返回数据不为空时 添加数据
        if (Utils.isNotEmpty(data)) {
            //当前获取第一页 清理原有数据 否则 后最后面添加新数据
            if (mStart == 1) {
                mDataList.clear();
                mDataList.addAll(data);
                mAdapter.setData(mDataList);
            } else {
                int start = mDataList.size();
                int end = data.size();
                mAdapter.notifyItemRangeData(start, end, data);
            }
        } else {
            //如果数据为空 但原有数据不为空 设置没有更多  否则显示空白页
            if (mDataList.size() != 0 && data.size() < mSize) {
                noMoreData();
            } else {
                switch (mSearchType) {
                    case 0:
                        listEmptyView.setNoSearchView();
                        break;
                    case 1:
                        listEmptyView.setNoDataView();
                        break;
                    case 2:
                        listEmptyView.setNoNetWorkView();
                        break;
                }
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
