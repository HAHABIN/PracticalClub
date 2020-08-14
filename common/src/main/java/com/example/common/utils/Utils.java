package com.example.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;

import com.example.common.R;

import java.io.File;


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


    /**
     * 拨打电话
     *
     * @param phoneNumber
     */
    public static void call(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            ToastUtils.show_s(context.getString(R.string.empty_phone));
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intent.setData(data);
        context.startActivity(intent);
    }
    public static int getAndroidSDKVersion() {
        int version = 0;
        try {
            version = Integer.valueOf(android.os.Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return version;
    }

    /***
     * 将指定路径的图片转uri
     * @param context
     * @param path ，指定图片(或文件)的路径
     * @return
     */
    public static Uri getMediaUriFromPath(Context context, String path) {
        Uri uri = null;
        String authority = context.getPackageName() + ".provider";
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            //如果是7.0android系统
            uri = FileProvider.getUriForFile(getContext(), authority, new File(path));
        } else {
            uri = Uri.fromFile(new File(path));
        }
        return uri;
    }
}

