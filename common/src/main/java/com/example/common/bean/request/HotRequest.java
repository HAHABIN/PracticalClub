/**
 * 文 件 名:  HotRequest
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/8/21
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.example.common.bean.request;

/**
 * <本周最热> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/21
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HotRequest extends BaseRequest {

    /** hot_type 可接受参数 views（浏览数） | likes（点赞数） | comments（评论数）❌*/
    private String hotType;
    /** ategory 可接受参数 Article | GanHuo | Girl*/
    private String category;

    /** count: [1, 20]*/
//    private int count;

    public HotRequest(String hotType, String category,int count) {
        super(count);
        this.hotType = hotType;
        this.category = category;

    }

    public String getHotType() {
        return hotType;
    }

    public void setHotType(String hotType) {
        this.hotType = hotType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
