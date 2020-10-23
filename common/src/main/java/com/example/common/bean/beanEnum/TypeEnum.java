package com.example.common.bean.beanEnum;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/21
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum TypeEnum {
    ANDROID("Android","Android"),
    IOS("iOS","iOS"),
    Flutter("Flutter","Flutter"),
    Girl("Girl","福利"),
    All("All","全部"),
    app("app","APP"),
    backend("backend","后端"),
    frontend("frontend","前端");

    private String type;

    private String name;

    TypeEnum(String type,String name) {
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
        for (TypeEnum typeEnum :TypeEnum.values()) {
            if (typeEnum.getName().equals(name)) {
                return typeEnum.getType();
            }
        }
        return "Android";
    }
    public static String getNameByType(String type) {
        for (TypeEnum typeEnum :TypeEnum.values()) {
            if (typeEnum.getType().equals(type)) {
                return typeEnum.getName();
            }
        }
        return "Android";
    }
}
