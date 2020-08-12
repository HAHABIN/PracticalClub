package com.example.common.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.R;
import com.example.common.UiUtils.DoubleUtils;
import com.example.common.UiUtils.StatusBarUtil;
import com.example.common.UiUtils.Utils;
import com.example.common.dialog.PictureDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Create by HABIN on 2019/11/4
 * Time：22:38
 * Email:739115041@qq.com
 * Activity基类
 * 继承AppCompatActivity主要是为了兼容低版本的一些问题；
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected static String TAG;
    protected Context mContext;
    private Unbinder mUnBinder;
    protected LinearLayout mainLayout;

    protected final int whiteStatusbar = 1;
    protected final int translucentStatusbar = 3;

    protected void statusBar(int status) {
        StatusBarUtil.transparencyBar(this);
        View statusbar = findViewById(R.id.status_bar_view_id);
        ViewGroup.LayoutParams params = statusbar.getLayoutParams();
        int height = StatusBarUtil.getStatusBarHeight(getBaseContext());
        switch (status) {
            case whiteStatusbar:
                statusbar.setBackgroundColor(getResources().getColor(R.color.color_white_navbar));
                StatusBarUtil.StatusBarLightMode(this);
                break;
            case translucentStatusbar:
                StatusBarUtil.StatusBarLightMode(this);
                height = 0;
                break;
        }
        params.height = height;
        statusbar.setLayoutParams(params);
    }
    /**
     * 加载窗
     */
    protected PictureDialog dialog;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.adaptScreen(this);
        mContext = this;
        //View注入
        mUnBinder = ButterKnife.bind(this);
        initMainLayout();

        processLogic();
        initParam();
        initView();
        initListener();
        initData();

    }

    private void initMainLayout() {
        mainLayout = (LinearLayout) View.inflate(this, R.layout.activity_base, null);
        setContentView(mainLayout);
        addNavBar();
        int contentViewId = getLayoutId();
        if (contentViewId != 0) {
            mainLayout.addView(View.inflate(this, getLayoutId(), null),
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        statusBar(whiteStatusbar);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化参数,包括intent传递过来的参数
     */
    protected void initParam(){}

    /**
     * 初始化view,包括初始化些控件
     */
    protected abstract void initView();

    /**
     * 初始化监听器,各种listener事件
     */
    protected abstract void initListener();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 执行逻辑
     */
    protected void processLogic() {
    }


    protected void addNavBar() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        Log.d(TAG, "onDestroy: " + TAG);
    }


    /**
     * loading dialog
     */
    public void showPleaseDialog() {
        if (!isFinishing()) {
            dismissDialog();
            dialog = new PictureDialog(this);
            dialog.show();
        }
    }

    /**
     * dismiss dialog
     */
    public void dismissDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void startActivity(Class clz, Bundle bundle) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent();
            intent.setClass(this, clz);
            if (bundle!=null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
        }
    }

    public void startActivity(Class clz, Bundle bundle, int requestCode) {
        if (!DoubleUtils.isFastDoubleClick()) {
            Intent intent = new Intent();
            intent.setClass(this, clz);
            if (bundle!=null) {
                intent.putExtras(bundle);
            }
            startActivityForResult(intent, requestCode);
        }
    }
}
