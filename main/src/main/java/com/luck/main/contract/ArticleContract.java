/**
 * 文 件 名:  ArticleContract
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/9/8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
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
