package com.common;

import com.google.gson.Gson;

public class Utils {

    public static String objectToJsonString(final Object obj) {
    	Gson gson = new Gson();
        String json = gson.toJson(obj);
        return json;
    }
}