/**
 * 文 件 名:  HotPresenter
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/9/1
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.luck.main.presenter;

import com.example.common.base.mvp.RxPresenter;
import com.example.common.bean.beanEnum.CategoryEnum;
import com.example.common.bean.beanEnum.HotEnum;
import com.example.common.bean.entity.BannerEntity;
import com.example.common.bean.entity.CategoryEntity;
import com.example.common.bean.entity.HotEntity;
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
    public void getHotData(HotEnum hotEnum, CategoryEnum categoryEnum) {
        //默认直接20条
        HotRequest hotRequest = new HotRequest(hotEnum.getType(),categoryEnum.getType(),20);
        HttpClient.getInstance().startTask(HttpHelper.TaskType.Hot,mView,hotRequest, HotEntity.class);
    }


}
