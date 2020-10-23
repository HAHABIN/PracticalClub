package com.example.common.bean.request;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/21
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BaseRequest {

    /** page>=1*/
    private int page;

    /** 范围 10-50*/
    private int count;

    public BaseRequest() {
    }

    public BaseRequest(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
