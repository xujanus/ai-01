package com.xujun365;

import java.io.IOException;
import java.util.Scanner;

/**
 * <p>ClassName:     Console
 * <p>Description:   控制台类，用于存放用于交互的代码
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/7
 */
public class Console {
    private static final String START_MESSAGE = "请输入：";

    /**
     * 使用System.in的原始方式，来读取控制台输入
     * @return 用户输入
     */
    public String consoleBySystemIn() throws IOException {
        String order = null;

        System.out.println(START_MESSAGE);

        //最大输入1K的字符命令
        byte[] buffer = new byte[1024];

        int state = System.in.read(buffer);
        if(state!=-1){
            order = new String(buffer).trim();
        }

        return order;
    }

    /**
     * 使用scanner类，来读取控制台输入
     * @return 用户输入
     */
    public String consoleByScanner(){
        System.out.println(START_MESSAGE);

        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public static void main(String[] args){
        System.out.println("请输入：");
        Scanner sc = new Scanner(System.in);
        System.out.println("wait1");
        System.out.println(sc.nextLine());
        System.out.println("wait2");
        System.out.println(sc.nextLine());
        System.out.println("done");
    }
}
