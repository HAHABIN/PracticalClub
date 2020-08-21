/**
 * 文 件 名:  MainPresenter
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/8/20
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.luck.main.presenter;

import com.example.common.base.mvp.RxPresenter;
import com.example.common.bean.CategoryEnum;
import com.example.common.bean.TypeEnum;
import com.example.common.bean.entity.CategoryEntity;
import com.example.common.bean.request.BaseRequest;
import com.example.common.bean.request.CategoryRequest;
import com.example.common.http.HttpClient;
import com.example.common.http.HttpHelper;
import com.luck.main.contract.MainContract;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/20
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter{


    @Override
    public void getDemo(CategoryEnum category, TypeEnum type, int page, int count) {
        CategoryRequest request = new CategoryRequest();
        request.setCategory(category.getType());
        request.setType(type.getType());
        request.setPage(page);
        request.setCount(count);
        HttpClient.getInstance().startTask(HttpHelper.TaskType.Girl,this,request, CategoryEntity.class);

    }
}
