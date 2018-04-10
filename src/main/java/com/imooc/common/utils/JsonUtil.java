package com.imooc.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by 廖师兄
 * 2017-07-04 01:30
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

    public static Object toObj(String json, Class clzz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, clzz);
    }
}
