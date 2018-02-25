package com.xujun365.order.list;

import com.xujun365.order.Order;

/**
 * <p>ClassName:     WeatherOrder
 * <p>Description:   天气命令的实现类
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/25
 */
public class WeatherOrder extends Order{
    @Override
    public void executeOrder() {
        System.out.println("你好，这里是天气情况，不过目前还没有实现哦，请下次再来");
    }
}
