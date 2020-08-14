package com.luck.main;


import android.os.Bundle;
import android.widget.ImageView;

import com.example.common.base.BaseActivity;
import com.example.common.base.WebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartupActivity extends BaseActivity {


    @BindView(R2.id.advert_photo_id)
    ImageView advertPhotoId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_startup;
    }

    @Override
    protected void initView() {
        advertPhotoId.setImageResource(R.drawable.ic_back);
        startActivity(WebActivity.class, null);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


}