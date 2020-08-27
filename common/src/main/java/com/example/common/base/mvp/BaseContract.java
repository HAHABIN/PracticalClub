package com.example.common.base.mvp;

import com.example.common.bean.HttpItem;
import com.example.common.http.ApiError;
import com.example.common.http.HttpHelper;


import org.json.JSONObject;

/**
 * Create by HABIN on 2019/11/4 22:28
 * Email:739115041@qq.com
 *
 * 将view、presenter、model的接口方法都串联在一起，更加便于管理
 */
public interface BaseContract {

    interface BasePresenter<T> {
        //绑定
        void attachView(T view);
        //解绑
        void detachView();
    }

    interface BaseView {
        void taskStarted(HttpHelper.TaskType type);
        void onSuccess(HttpHelper.TaskType type, HttpItem item);
        void onSuccess(HttpHelper.TaskType type, JSONObject object);
        void onFailure(HttpHelper.TaskType type, ApiError e);
    }
}
