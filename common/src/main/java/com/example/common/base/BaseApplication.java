/**
 * 文 件 名:  BaseApplication
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/8/11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.example.common.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.example.common.http.HttpClient;
import com.example.common.utils.ToastUtils;
import com.example.common.utils.Utils;
import com.tencent.smtt.sdk.QbSdk;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/11
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseApplication extends Application {

    private List<Activity> activitys = Collections.synchronizedList(new LinkedList<Activity>());

    @Override
    public void onCreate() {
        super.onCreate();
        HttpClient.getInstance().setContext(this);
        Utils.init(this);
        ToastUtils.init(this);

        registerActivityListener();
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                Log.e("xxx","hasLoad"+b);
                //此处将内核加载是否成功的状态保存到本地，SharePreference工具类可换为自己的
//                SharedPreferenceUtils.saveBoolean(getApplicationContext(),"hasLoad",b);
            }
        });
    }
    public void exit() {
        finishActivityList();
        System.exit(0);
    }

    private void finishActivityList() {
        if (activitys != null) {
            for (Activity activity : activitys) {
                activity.finish();
            }
        }
    }

    private void addActivity(Activity activity) {
        if (activitys != null) {
            if (!activitys.contains(activity)) {
                activitys.add(activity);
            }
        }
    }

    private void removeActivity(Activity activity) {
        if (activitys != null) {
            activitys.remove(activity);
        }
    }

    private void registerActivityListener() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                removeActivity(activity);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                removeActivity(activity);
            }
        });
    }
}
