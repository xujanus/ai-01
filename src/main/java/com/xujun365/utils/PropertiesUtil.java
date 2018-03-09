package com.xujun365.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * <p>ClassName:     PropertiesUtil
 * <p>Description:   主要用于读取Properties类型的配置文件
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/3/9
 */
public class PropertiesUtil {
    static final private String filePath = "/system.properties";

    //根据key读取value
    public static String readValue(String key) {
        Properties props = new Properties();
        try {
            //InputStream in = new BufferedInputStream (new FileInputStream(filePath));
            //使用下面这种方法更高效的原因是，上面那种方法，需要制定路径，而下面这种，是通过直接在classPath中找
            InputStream in = PropertiesUtil.class.getResourceAsStream(filePath);
            props.load(in);
            return props.getProperty (key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(PropertiesUtil.readValue("log.path"));
    }
}
