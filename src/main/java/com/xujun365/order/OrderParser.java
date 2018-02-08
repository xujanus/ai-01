package com.xujun365.order;

import com.xujun365.enums.OrderEnum;

/**
 * <p>ClassName:     OrderParser
 * <p>Description:   命令解析器
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/8
 */
public class OrderParser {

    public void parse(String word){
        Order order = OrderEnum.getOrderByWord(word);
        if(order==null){
            //// TODO: xujun 2018/2/8 用聊天指令重写
            System.out.println("找不到你的指令："+ word);
        }else {
            order.executeOrder();
        }
    }

    public static void main(String[] args) {
    }
}
