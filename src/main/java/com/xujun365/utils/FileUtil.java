package com.xujun365.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>ClassName:     FileUtil
 * <p>Description:   文件相关的工具类
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/3/1
 */
public class FileUtil {
    public static String readFile(String path) {
        String result = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                if(result == null){
                    result = tempStr;
                }else {
                    result = result + tempStr;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void writeFile(String path, String content){
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(readFile("E:\\github\\local_db\\chatDB.json"));

        writeFile("E:\\github\\local_db\\chatDB2.json","text test");
    }

}
