package com.xujun365.order;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <p>ClassName:     OrderParserTest
 * <p>Description:   指令解析类的单元测试
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/8
 */
public class OrderParserTest {
    @Test
    public void parse() throws Exception {
        String order = "测试指令解析";
        OrderParser orderParser = new OrderParser();
        orderParser.parse(order);
    }

}