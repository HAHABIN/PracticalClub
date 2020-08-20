package com.luck.main.activity;

import com.example.common.http.HttpItem;
import com.example.common.base.mvp.BaseMVPActivity;
import com.example.common.http.ApiError;
import com.example.common.http.HttpHelper;
import com.luck.main.R;
import com.luck.main.contract.MainContract;
import com.luck.main.presenter.MainPresenter;

import org.json.JSONObject;

public class MainActivity extends BaseMVPActivity<MainContract.Presenter> implements MainContract.View  {

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

    }

    @Override
    protected MainContract.Presenter bindPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onSuccess(HttpHelper.TaskType type, HttpItem item) {

    }

    @Override
    public void onSuccess(HttpHelper.TaskType type, JSONObject object) {

    }

    @Override
    public void onFailure(HttpHelper.TaskType type, ApiError e) {

    }
}