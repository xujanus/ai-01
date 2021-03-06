package com.xujun365;

import com.xujun365.enums.OrderEnum;
import com.xujun365.order.OrderParser;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>ClassName:     Main
 * <p>Description:   程序主方法
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/6
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("AI程序已启动...");
        Console console = new Console();
        //用户指令
        String order;

        do {
            order = console.consoleByScanner();
            OrderParser orderParser = new OrderParser();
            orderParser.parse(order);

        } while (!OrderEnum.EXIST.getWord().equals(order));
    }

    public void test(String args) {
        System.out.println(args);
    }
}
