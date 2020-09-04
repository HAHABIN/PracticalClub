
package com.luck.main.contract;

import com.example.common.base.mvp.BaseContract;
import com.example.common.bean.beanEnum.CategoryEnum;
import com.example.common.bean.beanEnum.HotEnum;
import com.example.common.bean.beanEnum.TypeEnum;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/9/1
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface HotContract extends BaseContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<HotContract.View>{
        /**
         * 获取轮播图
         */
        void getBanner();

        /**
         * 获取热门
         * @param hotEnum  热门类型
         * @param categoryEnum  类型
         *
         */
        void getHotData(HotEnum hotEnum, CategoryEnum categoryEnum);
    }
}