package com.example.common.bean.beanEnum;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/21
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum CategoryEnum {

    GanHuo("GanHuo","干货"),
    Article("Article","文章"),
    Girl("Girl","福利");


    private String type;
    private String name;

    CategoryEnum( String type,String name) {
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
        for (CategoryEnum categoryEnum :CategoryEnum.values()) {
            if (categoryEnum.getName().equals(name)) {
                return categoryEnum.getType();
            }
        }
        return "GanHuo";
    }
    public static String getNameByType(String type) {
        for (CategoryEnum categoryEnum :CategoryEnum.values()) {
            if (categoryEnum.getType().equals(type)) {
                return categoryEnum.getName();
            }
        }
        return "干货";
    }
}
