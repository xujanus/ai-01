package com.xujun365;

import java.io.IOException;

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
     * 使用System.in的原始交互方式
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

    public static void main(String[] args) throws IOException {
        Console console = new Console();
        String order = console.consoleBySystemIn();
        System.out.println("您输入的指令是："+order);
    }
}
