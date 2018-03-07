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
        //命令解析，这里的命令只能通过单一的关键字触发。命令的其它使用方法，可以通过聊天模式解析触发。
        Order order = OrderEnum.getOrderByWord(word);
        //如果命令不存在，进入聊天模式。部分命令可以在聊天模式下触发，如“天气”
        if(order==null){
            new ChatOrder(word).executeOrder();
        }else {
            order.executeOrder();
        }
    }

    public static void main(String[] args) {
    }
}
