package com.example.common.bean.entity;

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
