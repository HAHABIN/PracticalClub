package com.luck.main.activity;

import android.widget.TextView;


import com.example.common.base.mvp.BaseMVPActivity;
import com.example.common.bean.CategoryEnum;
import com.example.common.bean.HttpItem;
import com.example.common.bean.TypeEnum;
import com.example.common.bean.entity.CategoryEntity;
import com.example.common.http.ApiError;
import com.example.common.http.HttpHelper;
import com.example.common.utils.ToastUtils;
import com.luck.main.R;
import com.luck.main.R2;
import com.luck.main.contract.MainContract;
import com.luck.main.presenter.MainPresenter;

import org.json.JSONObject;

import butterknife.BindView;

public class MainActivity extends BaseMVPActivity<MainContract.Presenter> implements MainContract.View  {

    @BindView(R2.id.tv_content)
    TextView mTvContent;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.getDemo(CategoryEnum.Ganhuo, TypeEnum.ANDROID,1,10);
    }

    @Override
    protected MainContract.Presenter bindPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onSuccess(HttpHelper.TaskType type, HttpItem item) {
        if (item instanceof CategoryEntity) {
            mTvContent.setText(((CategoryEntity) item).getData().get(0).toString());
        }

    }

    @Override
    public void onSuccess(HttpHelper.TaskType type, JSONObject object) {

    }

    @Override
    public void onFailure(HttpHelper.TaskType type, ApiError e) {
        ToastUtils.show_s(e.getMessage());
    }
}