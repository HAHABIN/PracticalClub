package com.example.common.utils;

import android.util.Log;

import com.orhanobut.logger.LogStrategy;

/**
 * create by Vincent on 2019/6/13 11:06
 * describe :
 */
public class LogCatStrategy implements LogStrategy {

    @Override
    public void log(int priority, String tag, String message) {
        Log.println(priority, randomKey() + tag, message);
    }

    private int last;

    private String randomKey() {
        int random = (int) (10 * Math.random());
        if (random == last) {
            random = (random + 1) % 10;
        }
        last = random;
        return String.valueOf(random);
    }
}