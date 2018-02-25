package com.xujun365.enums;

import com.xujun365.order.Order;
import com.xujun365.order.list.*;
import lombok.Getter;

/**
 * <p>ClassName:     OrderEnum
 * <p>Description:   存放用户输入的指令集合
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/8
 */
public enum OrderEnum {
    EXIST("退出", new ExistOrder()),
    CHAT("聊天", new ChatOrder()),
    WEATHER("天气", new WeatherOrder());

    //指令的口令
    @Getter
    String word;

    //指令
    @Getter
    Order order;

    //用于未实现的指令使用，冗余设计
    OrderEnum(String word) {
        this.word = word;
        this.order = null;
    }

    OrderEnum(String word, Order order) {
        this.word = word;
        this.order = order;
    }

    public static Order getOrderByWord(String word) {
        if (word == null) {
            return null;
        }

        for (OrderEnum item : values()) {
            if (word.equals(item.getWord())) {
                return item.getOrder();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        OrderEnum.getOrderByWord("退出").executeOrder();
        System.out.println("的");
    }
}
