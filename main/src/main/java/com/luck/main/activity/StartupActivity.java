package com.luck.main.activity;


import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.common.base.BaseActivity;
import com.example.common.dialog.AlertDialogView;
import com.example.common.utils.Utils;
import com.luck.main.R;
import com.luck.main.activity.MainActivity;

public class StartupActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_startup;
    }

    @Override
    protected void initView() {
        statusBar(StatusBarEnum.TRANSLUCENT);
        restart(Utils.isNetworkConnected());

    }



    private void restart(Boolean flag){
        if (!flag){
            AlertDialogView dialogView = new AlertDialogView(this);
            dialogView.setTitle("网络请求");
            dialogView.setMessage("当前无网络，请开启网络");
            dialogView.setConfimStr("退出");
            dialogView.setCancelStr("重试");
            dialogView.setListener(new AlertDialogView.onClickListener() {
                @Override
                public void cancelClick(AlertDialogView dialog) {
                    restart(Utils.isNetworkConnected());
                }
                @Override
                public void confirmClick(AlertDialogView dialog) {
                    finish();
                }
            });
            dialogView.show();
        } else {
            StartUp();
        }
    }
    private void StartUp() {
        LinearLayout layoutSplash = findViewById(R.id.activity_startup);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(2000);//设置动画播放时长1000毫秒（1秒）
        layoutSplash.startAnimation(alphaAnimation);
        //设置动画监听
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            //动画结束
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(MainActivity.class,null);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


}