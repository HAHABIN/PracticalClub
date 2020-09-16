package com.luck.main.presenter;

import com.example.common.base.mvp.RxPresenter;
import com.example.common.bean.beanEnum.CategoryEnum;
import com.example.common.bean.beanEnum.TypeEnum;
import com.example.common.bean.entity.CategoryEntity;
import com.example.common.bean.request.CategoryRequest;
import com.example.common.http.HttpClient;
import com.example.common.http.HttpHelper;
import com.luck.main.contract.GirlContract;

/**
 * <一句话功能简述> <功能详细描述>
 * @author HABIN
 * @version 2020/8/25
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class GirlPresenter extends RxPresenter<GirlContract.View> implements GirlContract.Presenter {

    @Override
    public void getGirl(int page, int count) {
        CategoryRequest request = new CategoryRequest();
        request.setCategory(CategoryEnum.Girl.getType());
        request.setType(TypeEnum.Girl.getType());
        request.setPage(page);
        request.setCount(count);
        HttpClient.getInstance().startTask(HttpHelper.TaskType.Girl,mView,request, CategoryEntity.class);
    }
}
