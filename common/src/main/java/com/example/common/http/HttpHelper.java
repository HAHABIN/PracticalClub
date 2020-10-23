package com.example.common.http;

import com.example.common.R;
import com.example.common.bean.request.BaseRequest;
import com.example.common.bean.request.CategoryRequest;
import com.example.common.bean.request.HotRequest;
import com.example.common.bean.request.SearchRequest;
import com.example.common.utils.Utils;

public class HttpHelper {

    public enum TaskType {

        Girl,       //女孩
        GanHuo,     //干货
        Article,    //文章
        Banners,    //轮播图
        Hot,        //本周最热
        search,     //搜索
        Random,     //随机

    }


    public static String getMethod(TaskType type, BaseRequest request) {
        String method = "";
        switch (type) {

            case Girl:
            case GanHuo:
            case Article:
                CategoryRequest categoryRequest = (CategoryRequest) request;
                method = String.format(Utils.getContext().getString(R.string.string_get_category)
                        , categoryRequest.getCategory()
                        , categoryRequest.getType()
                        , categoryRequest.getPage()
                        , categoryRequest.getCount());

                break;
            case Banners:
                method = "banners";
                break;
            case Hot:
                HotRequest hotRequest = (HotRequest) request;
                method = String.format(Utils.getContext().getString(R.string.string_get_hot)
                        , hotRequest.getHotType()
                        , hotRequest.getCategory()
                        , hotRequest.getCount());

                break;
            case search:
                SearchRequest searchRequest = (SearchRequest) request;
                method = String.format(Utils.getContext().getString(R.string.string_get_search)
                        , searchRequest.getSearch()
                        , searchRequest.getCategory()
                        , searchRequest.getType()
                        , searchRequest.getPage()
                        , searchRequest.getCount());
                break;
            case Random:
                CategoryRequest random = (CategoryRequest) request;
                method = String.format(Utils.getContext().getString(R.string.string_get_random)
                        , random.getCategory()
                        , random.getType()
                        , random.getCount());
                break;

        }
        return method;
    }
}
