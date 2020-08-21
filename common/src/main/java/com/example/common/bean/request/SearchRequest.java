/**
 * 文 件 名:  SearchRequest
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
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/21
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SearchRequest extends BaseRequest {

    /**
     * search 可接受参数 要搜索的内容
     */
    private String search;

    /**
     * category 可接受参数 All[所有分类] | Article | GanHuo
     */
    private String category;

    /**
     * type 可接受参数 Android | iOS | Flutter ...，即分类API返回的类型数据
     */
    private String type;



}
