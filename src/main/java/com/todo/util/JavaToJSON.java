package com.todo.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JavaToJSON {
    public static JSONObject javaToJSON(Object object){
        JSONObject jsonObj = (JSONObject) JSON.toJSON(object);
        return jsonObj;
    }
}
