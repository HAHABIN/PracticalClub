package com.example.common.bean.entity;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/10/13
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Company {

    private String province;

    private int count;

    public Company(String province, int count) {
        this.province = province;
        this.count = count;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
