package com.xujun365.model;

import lombok.Data;

import java.util.List;

/**
 * <p>ClassName:     ChatDataBase
 * <p>Description:   聊天机器人的数据库
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/25
 */
@Data
public class ChatDataBaseModel {
    //数据库版本
    final int version = 1;
    List<ChatRecordModel> recordList;

}
