package com.example.common.http;

import java.util.HashMap;

public class HttpHelper {

    public enum TaskType {

        Girl, //女孩
        GanHuo,//干货
        Article, //文章
        
    }


    public static String getMethod(TaskType type, HashMap<String, Object> params) {
        String method = "";
        switch (type) {

            case Girl:
                method = "loadadmin/logincheck";
                break;
        }
        return method;
    }
}
