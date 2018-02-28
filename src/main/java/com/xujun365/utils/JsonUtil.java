package com.xujun365.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>ClassName:     JsonUtil
 * <p>Description:   json相关的工具类
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/25
 */
public class JsonUtil {
    public static String pojoToJson(Object pojo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonToPojo(String content, Class<T> valueType) {
        if (content == null) return null;
        ObjectMapper mapper = new ObjectMapper(); //转换器
        try {
            return mapper.readValue(content, valueType);
        } catch (IOException e) {
            //如果无法解析成json，则直接返回，放弃给其它变量赋值
            return null;
        }
    }

    //本地测试
    public static void main(String[] args) {
        ArrayList<String> test = new ArrayList<>();
        test.add("3");
        test.add("4");
        System.out.println(pojoToJson(test));
        String str = "[\"1\",\"3\",\"4\"]";
        ArrayList test2 = jsonToPojo(str, ArrayList.class);
        System.out.println(test2);
    }

}
