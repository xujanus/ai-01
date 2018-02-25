package com.xujun365.order.list;

import com.xujun365.order.Order;

/**
 * <p>ClassName:     ChatOrder
 * <p>Description:   聊天命令的执行类
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/25
 */
public class ChatOrder extends Order {
    String lastWord;

    public ChatOrder() {
        this.lastWord = null;
    }

    public ChatOrder(String lastWord) {
        this.lastWord = lastWord;
    }

    @Override
    public void executeOrder() {
        if(lastWord == null || "".equals(lastWord)){
            System.out.println("你好，我是AI-01，很高兴能和你聊天~");
        }else {
            System.out.println("你好，我暂时只是一台复读机，你刚才说的话是："+ lastWord);
        }
    }
}
