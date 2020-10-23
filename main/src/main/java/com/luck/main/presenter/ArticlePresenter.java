package com.luck.main.presenter;

import com.example.common.base.mvp.RxPresenter;
import com.example.common.bean.entity.CategoryEntity;
import com.example.common.bean.request.CategoryRequest;
import com.example.common.http.HttpClient;
import com.example.common.http.HttpHelper;
import com.luck.main.contract.ArticleContract;

/**
 * <一句话功能简述> <功能详细描述>
 * @author HABIN
 * @version 2020/8/25
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ArticlePresenter extends RxPresenter<ArticleContract.View> implements ArticleContract.Presenter {



    @Override
    public void getData(HttpHelper.TaskType taskType,String category, String type, int page, int count) {
        CategoryRequest request = new CategoryRequest();
        request.setCategory(category);
        request.setType(type);
        request.setPage(page);
        request.setCount(count);
        HttpClient.getInstance().startTask(taskType,mView,request, CategoryEntity.class);
    }
}
