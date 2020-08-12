package com.example.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;




/**
 *
 * Class desc: ui 操作相关封装
 */
public class Utils {


    public static int getScreenWidth(Context activity) {
        return activity.getResources().getDisplayMetrics().widthPixels;
    }

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("should be initialized in application");
    }

    //判断是否有网络
    public static boolean isNetworkConnected() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }
    /**
     * 适配的主要代码
     *
     * @param activity        上下文
     * @param sizeInPx        你要适配的相应尺寸
     * @param isVerticalSlide 水平还是垂直为参考
     */
    public static void adaptScreen(final Activity activity) {

        boolean isVerticalSlide = true;
        int sizeInPx = 375;
        // 系统的屏幕尺寸
        final DisplayMetrics systemDm = Resources.getSystem().getDisplayMetrics();
        // app整体的屏幕尺寸
        final DisplayMetrics appDm = Utils.getContext().getResources().getDisplayMetrics();
        // activity的屏幕尺寸
        final DisplayMetrics activityDm = activity.getResources().getDisplayMetrics();
        if (isVerticalSlide) {
            activityDm.density = activityDm.widthPixels / (float) sizeInPx;
        } else {
            activityDm.density = activityDm.heightPixels / (float) sizeInPx;
        }
        // 字体的缩放因子，这个是通过一个比例计算得来的！
        activityDm.scaledDensity = activityDm.density * (systemDm.scaledDensity / systemDm.density);
        // 计算得到相应的dpi
        activityDm.densityDpi = (int) (160 * activityDm.density);

        //进行相应的赋值操作
        appDm.density = activityDm.density;
        appDm.scaledDensity = activityDm.scaledDensity;
        appDm.densityDpi = activityDm.densityDpi;
    }
    public static int dipPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

