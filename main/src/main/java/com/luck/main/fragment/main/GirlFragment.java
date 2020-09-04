package com.luck.main.fragment.main;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.common.base.SwipeRecyclerFragment;
import com.example.common.base.adapter.CommonAdapter;
import com.example.common.bean.HttpItem;
import com.example.common.bean.entity.CategoryEntity;
import com.example.common.http.ApiError;
import com.example.common.http.HttpHelper;
import com.example.common.utils.ToastUtils;
import com.example.common.utils.Utils;
import com.example.common.widget.view.ListEmptyView;
import com.luck.main.adapter.GirlAdapter;
import com.luck.main.contract.GirlContract;
import com.luck.main.presenter.GirlPresenter;

import org.json.JSONObject;
import java.util.ArrayList;


public class  GirlFragment extends SwipeRecyclerFragment<GirlContract.Presenter> implements GirlContract.View {



    @Override
    protected void initListener() {
        super.initListener();
        addLoadMoreView();
        startRefresh();
    }

    @Override
    public CommonAdapter getAdapter() {
        return new GirlAdapter(getContext());
    }

    @Override
    protected GirlContract.Presenter bindPresenter() {
        return new GirlPresenter();
    }


    @Override
    protected void RequestData() {
        showPleaseDialog();
        mPresenter.getGirl(mStart,mSize);
    }

    @Override
    protected void setLayoutManager() {
        super.setLayoutManager();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        layoutManager.setReverseLayout(false);
        swipeRecyclerView.getRecyclerView().setLayoutManager(layoutManager);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) swipeRecyclerView.getLayoutParams();
        params.setMargins(Utils.dipPx(8), 0, Utils.dipPx(8), 0);
        swipeRecyclerView.setLayoutParams(params);
    }

    @Override
    protected View setEmptyView() {
        return new ListEmptyView(getContext());
    }

    @Override
    public void taskStarted(HttpHelper.TaskType type) {

    }

    @Override
    public void onSuccess(HttpHelper.TaskType type, HttpItem item) {
        dismissDialog();
        switch (type) {
            case Girl:
                if (item instanceof CategoryEntity) {
                    ArrayList<CategoryEntity.ResultBean> data = ((CategoryEntity) item).getData();
                    setAdapterData(data);
                }
        }
    }

    @Override
    public void onSuccess(HttpHelper.TaskType type, JSONObject object) {
        dismissDialog();
    }

    @Override
    public void onFailure(HttpHelper.TaskType type, ApiError e) {
        dismissDialog();
        ToastUtils.show_s(e.getMessage());
    }
}