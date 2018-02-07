package com.xujun365;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * <p>ClassName:     ConsoleTest
 * <p>Description:   控制台的单元测试
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/7
 */
public class ConsoleTest {
    @Test
    public void consoleBySystemIn() throws Exception {
        String testOrder = "this is a order!";
        String testOrderResult;

        InputStream si = System.in;
        try{
            //重新分配“标准”输入流
            System.setIn(new ByteArrayInputStream(testOrder.getBytes()));

            Console console = new Console();
            testOrderResult = console.consoleBySystemIn();
        }finally {
            System.setIn(si);
        }

        System.out.println(testOrder);
        assertTrue(testOrder.equals(testOrderResult));
    }

}