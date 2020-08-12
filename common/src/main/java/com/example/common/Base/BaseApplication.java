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
package com.example.common.Base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.example.common.Http.HttpClient;
import com.example.common.UiUtils.Utils;

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
        registerActivityListener();

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
