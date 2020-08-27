package com.luck.main.contract;

import com.example.common.base.mvp.BaseContract;
import com.example.common.bean.beanEnum.CategoryEnum;
import com.example.common.bean.beanEnum.TypeEnum;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/20
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MainContract extends BaseContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View>{
        void getDemo(CategoryEnum category, TypeEnum type, int page, int count);
    }

}