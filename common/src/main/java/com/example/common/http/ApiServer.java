package com.example.common.http;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Create by HABIN on 2019/11/5
 * Time：23:50
 * Email:739115041@qq.com
 *
 * 请求体的创建。
 */
public interface ApiServer {


//    @FormUrlEncoded
//    //添加请求头注解 解决中文乱码
//    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
//    @POST("{path}")
//    Observable<Response<ResponseBody>> postJSON(@Path(value = "path"
//            , encoded = true) String path
//            , @FieldMap Map<String, Object> param);

    @GET("{path}")
    Observable<Response<ResponseBody>> postJSON(@Path(value = "path") String path);
}
