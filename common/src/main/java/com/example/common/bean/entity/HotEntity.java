/**
 * 文 件 名:  HotEntity
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/8/27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.example.common.bean.entity;

import android.os.Parcelable;

import com.example.common.bean.HttpItem;

import java.util.ArrayList;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/27
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HotEntity extends HttpItem {

    private ArrayList<CategoryEntity.ResultBean> data;

    private String category;

    public ArrayList<CategoryEntity.ResultBean> getData() {
        return data;
    }

    public void setData(ArrayList<CategoryEntity.ResultBean> data) {
        this.data = data;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
