package com.xujun365.order.list;

import com.xujun365.order.Order;

/**
 * <p>ClassName:     ExistOrder
 * <p>Description:   提出命令的执行类
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/8
 */
public class ExistOrder extends Order {
    @Override
    public void executeOrder() {
        System.out.println("再见！");
    }
}
