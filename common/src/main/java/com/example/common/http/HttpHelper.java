package com.example.common.http;

import com.example.common.bean.request.BaseRequest;
import com.example.common.bean.request.CategoryRequest;

import java.util.HashMap;

public class HttpHelper {

    public enum TaskType {

        Girl, //女孩
        GanHuo,//干货
        Article, //文章

    }



    public static String getMethod(TaskType type, BaseRequest request) {
        String method = "";
        switch (type) {

            case Girl:
            case GanHuo:
            case Article:
                CategoryRequest categoryRequest = (CategoryRequest) request;
                method = "data/category/Article/type/"+categoryRequest.getType()+"/page/"+categoryRequest.getPage()+"/count/"+categoryRequest.getCount();
                break;

        }
        return method;
    }
}
