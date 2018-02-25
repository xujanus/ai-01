package com.xujun365.order;

import com.xujun365.enums.OrderEnum;
import com.xujun365.order.list.ChatOrder;

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
            new ChatOrder(word).executeOrder();
        }else {
            order.executeOrder();
        }
    }

    public static void main(String[] args) {
    }
}
