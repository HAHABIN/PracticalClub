package com.example.common.Http;

public class HttpHelper {

    public enum TaskType {

        Login, //登陆
    }

    public static String getMethod(TaskType type) {
        String method = "";
        switch (type) {

            case Login:
                method = "loadadmin/logincheck";
                break;
        }
        return method;
    }
}
