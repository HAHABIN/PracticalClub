package com.luck.main.presenter;

import com.example.common.base.mvp.RxPresenter;
import com.example.common.bean.beanEnum.CategoryEnum;
import com.example.common.bean.beanEnum.HotEnum;
import com.example.common.bean.beanEnum.TypeEnum;
import com.example.common.bean.entity.BannerEntity;
import com.example.common.bean.entity.CategoryEntity;
import com.example.common.bean.entity.HotEntity;
import com.example.common.bean.request.CategoryRequest;
import com.example.common.bean.request.HotRequest;
import com.example.common.http.HttpClient;
import com.example.common.http.HttpHelper;
import com.luck.main.contract.HotContract;
import com.luck.main.contract.HotContract.Presenter;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/9/1
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HotPresenter extends RxPresenter<HotContract.View> implements HotContract.Presenter {


    @Override
    public void getBanner() {
        HttpClient.getInstance().startTask(HttpHelper.TaskType.Banners,mView,null, BannerEntity.class);
    }

    @Override
    public void getHotData(String hot, String category) {
        //默认直接20条
        HotRequest hotRequest = new HotRequest(hot,category,20);
        HttpClient.getInstance().startTask(HttpHelper.TaskType.Hot,mView,hotRequest, HotEntity.class);
    }

    @Override
    public void getRandom(String category, String type) {
        //默认直接20条
        CategoryRequest random = new CategoryRequest();
        random.setCategory(category);
        random.setType(type);
        random.setCount(20);
        HttpClient.getInstance().startTask(HttpHelper.TaskType.Random,mView,random, CategoryEntity.class);
}


}
