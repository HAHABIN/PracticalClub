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

    public SearchRequest(String search, String category, String type) {
        this.search = search;
        this.category = category;
        this.type = type;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
