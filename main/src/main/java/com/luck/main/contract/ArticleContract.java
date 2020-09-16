package com.luck.main.contract;

import com.example.common.base.mvp.BaseContract;
import com.example.common.bean.beanEnum.TypeEnum;
import com.example.common.bean.request.CategoryRequest;
import com.example.common.http.HttpHelper;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/9/8
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ArticleContract extends BaseContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<ArticleContract.View>{
        void getData(HttpHelper.TaskType taskType, String category, String type, int page, int count);
    }

}
