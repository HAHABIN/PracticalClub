/**
 * 文 件 名:  ArticleFragment
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/9/8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.luck.main.fragment.main;

import android.view.View;

import com.example.common.base.SwipeRecyclerFragment;
import com.example.common.base.adapter.CommonAdapter;
import com.example.common.base.mvp.BaseContract;
import com.example.common.base.mvp.BaseMVPFragment;
import com.example.common.bean.HttpItem;
import com.example.common.bean.beanEnum.CategoryEnum;
import com.example.common.bean.beanEnum.TypeEnum;
import com.example.common.bean.entity.CategoryEntity;
import com.example.common.http.ApiError;
import com.example.common.http.HttpHelper;
import com.example.common.widget.view.ListEmptyView;
import com.luck.main.R;
import com.luck.main.adapter.NewsAdapter;
import com.luck.main.contract.ArticleContract;
import com.luck.main.presenter.ArticlePresenter;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/9/8
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ArticleFragment extends SwipeRecyclerFragment<ArticleContract.Presenter> implements ArticleContract.View {


    private String type = TypeEnum.All.getType();
    @Override
    public CommonAdapter getAdapter() {
        return new NewsAdapter(getContext());
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    protected View setEmptyView() {
        return new ListEmptyView(getContext());
    }

    @Override
    protected ArticleContract.Presenter bindPresenter() {
        return new ArticlePresenter();
    }

    @Override
    protected void RequestData() {
        mPresenter.getData(HttpHelper.TaskType.Article
                ,CategoryEnum.Article.getType()
                ,type,mStart,mSize);
    }

    @Override
    protected void initListener() {
        super.initListener();
        addLoadMoreView();
        startRefresh();
    }

    @Override
    public void taskStarted(HttpHelper.TaskType type) {

    }

    @Override
    public void onSuccess(HttpHelper.TaskType type, HttpItem item) {
        dismissDialog();
        switch (type) {
            case Article:
                if (item instanceof CategoryEntity) {
                    ArrayList<CategoryEntity.ResultBean> data = ((CategoryEntity) item).getData();
                    setAdapterData(data);
                }
        }
    }

    @Override
    public void onSuccess(HttpHelper.TaskType type, JSONObject object) {

    }

    @Override
    public void onFailure(HttpHelper.TaskType type, ApiError e) {

    }
}
