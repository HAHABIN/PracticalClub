package com.example.common.bean.beanEnum;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/27
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum HotEnum {

    views("views","浏览数"),   //浏览数
    likes("likes","点赞数"),   //点赞数
    comments("comments","评论数");//评论数


    private String type;
    private String name;

    HotEnum( String type,String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static String getTypeByName(String name) {
        for (HotEnum hotEnum :HotEnum.values()) {
            if (hotEnum.getName().equals(name)) {
                return hotEnum.getType();
            }
        }
        return "views";
    }
    public static String getNameByType(String type) {
        for (HotEnum hotEnum :HotEnum.values()) {
            if (hotEnum.getType().equals(type)) {
                return hotEnum.getName();
            }
        }
        return "浏览数";
    }
}
