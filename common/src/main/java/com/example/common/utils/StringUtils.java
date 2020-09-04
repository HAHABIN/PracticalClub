package com.example.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhouas666 on 2017/12/12.
 */
public class StringUtils {


    @SuppressLint("StaticFieldLeak")
    private static Context context;

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        StringUtils.context = context.getApplicationContext();
    }

    /**
     * 验证邮箱的正则表达式
     * @param email
     * @return
     */
    public static boolean checkEmail(String email)
    {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 验证手机号码，11位数字，1开通，第二位数必须是3456789这些数字之一 *
     * @param mobileNumber
     * @return
     */
    public static boolean checkPhoneNumber(String mobileNumber) {
        boolean flag = false;
        if (mobileNumber.length()==0){
            return false;
        }
        try {
            // Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
            Pattern regex = Pattern.compile("^1[345789]\\d{9}$");
            Matcher matcher = regex.matcher(mobileNumber);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }

    public static void main(String[] args) {
        String name = "中中中中中";
        boolean b = checkName(name);
        System.out.println(b);
    }
    /**
     * 验证帐号和密码，6-12字符长度范围
     * @param str
     * @return
     */
    public static boolean checkLenth(String str){
        boolean flag = true;
        if (str.length()==0){
            return false;
        }
        if (str.length()>12||str.length()<5){
            return false;
        }
        return flag;
    }
    /**
     * 验证帐号和密码，6-12位字母数字
     * @param str
     * @return
     */
    public static boolean checkName(String str) {
        boolean flag = false;
        if (str.length()==0){
            return false;
        }
        try {
            Pattern regex = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{5,12}$");
            Matcher matcher = regex.matcher(str);
            flag = matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;

        }
        return flag;
    }
    /**
     * 随机返回颜色Color十六进制字符串
     * @return
     */
    public static String randomColor(){
        //红色
        String red;
        //绿色
        String green;
        //蓝色
        String blue;
        //生成随机对象
        Random random = new Random();
        //生成红色颜色代码
        red = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成绿色颜色代码
        green = Integer.toHexString(random.nextInt(256)).toUpperCase();
        //生成蓝色颜色代码
        blue = Integer.toHexString(random.nextInt(256)).toUpperCase();

        //判断红色代码的位数
        red = red.length()==1 ? "0" + red : red ;
        //判断绿色代码的位数
        green = green.length()==1 ? "0" + green : green ;
        //判断蓝色代码的位数
        blue = blue.length()==1 ? "0" + blue : blue ;
        //生成十六进制颜色值
        return "#"+red+green+blue;
    }


    /*
     * 将时间戳转换为时间
     *
     * s就是时间戳
     */

    public static String stampToDate(long time){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //如果它本来就是long类型的,则不用写这一步
//        long lt = new Long(s);
        Date date = new Date(time);
        res = simpleDateFormat.format(date);
        return res;
    }
    /**
     * String str = "2019-03-13 ";
     * 时间字符串转为时间戳
     * */
    public static long  dateToStamp(String str)  {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();

    }


    /**
     * 时间差
     *
     * @param lDate
     * @return
     */
    public static String getTimeFormatText(long lDate) {
        long minute = 60 * 1000;// 1分钟
        long hour = 60 * minute;// 1小时
        long day = 24 * hour;// 1天
        long month = 31 * day;// 月
        long year = 12 * month;// 年
        if (lDate == 0) {
            return null;
        }
//        Date date = new Date(lDate);

        long diff = new Date().getTime() - lDate;
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 拼接
     * @param string
     * @param url
     * @return
     */
    public static String formatString(int string,String url) {
        return String.format(context.getString(string),url);
    }
}
