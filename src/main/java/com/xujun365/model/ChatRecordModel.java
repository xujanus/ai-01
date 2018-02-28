package com.xujun365.model;

import lombok.Data;

import java.util.Set;

/**
 * <p>ClassName:     ChatRecordModel
 * <p>Description:   单条聊天记录的数据模型
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/25
 */
@Data
public class ChatRecordModel {
    Set<String> keyWord;
    String reply;
}
