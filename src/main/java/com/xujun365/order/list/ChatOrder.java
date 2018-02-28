package com.xujun365.order.list;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xujun365.model.ChatDataBaseModel;
import com.xujun365.model.ChatRecordModel;
import com.xujun365.order.Order;
import com.xujun365.utils.JsonUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>ClassName:     ChatOrder
 * <p>Description:   聊天命令的执行类
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/25
 */
public class ChatOrder extends Order {
    private static final String NO_LAST_SENTENCE_REPLAY = "你好，我是AI-01，很高兴能和你聊天~";
    private static final String CAN_NO_ANALYSIS_SENTENCE_REPLAY = "抱歉，我不知道该怎么回答你哦";
    String lastSentence;

    public ChatOrder() {
        this.lastSentence = null;
    }

    public ChatOrder(String lastSentence) {
        this.lastSentence = lastSentence;
    }

    @Override
    public void executeOrder() {
        System.out.println(reply(lastSentence));
    }

    //回话
    private String reply(String lastSentence) {
        if (lastSentence == null || "".equals(lastSentence)) {
            return NO_LAST_SENTENCE_REPLAY;
        }

        return analysisSentence(lastSentence);
    }

    //解析对话
    private String analysisSentence(String lastSentence) {
        String result = searchReplyFromDB(lastSentence);
        if (result == null) result = CAN_NO_ANALYSIS_SENTENCE_REPLAY;
        return result;
    }

    //从数据库中搜索回答
    private String searchReplyFromDB(String lastSentence) {
        String result = null;
        ChatDataBaseModel chatDataBaseModel = getChatDataBaseModel();
        List<ChatRecordModel> recordList = chatDataBaseModel.getRecordList();
        //遍历每条数据，以后可以考虑优化，提升搜索效率
        for(ChatRecordModel record : recordList){
            Set<String> keyWord = record.getKeyWord();
            if(wordMatchSentence(keyWord, lastSentence)){
                result = record.getReply();
                break;
            }

        }

        return result;
    }

    //检查词组是否与语句匹配
    private boolean wordMatchSentence(Set<String> keyWord, String lastSentence) {
        for(String word : keyWord){
            if(!lastSentence.contains(word)){
                return false;
            }
        }
        return true;
    }

    //获取聊天资料库
    private ChatDataBaseModel getChatDataBaseModel() {
        //// TODO: xujun 2018/2/28 待实现，读缓存，从本地文件
        ChatDataBaseModel result = new ChatDataBaseModel();
        List<ChatRecordModel> recordList = new ArrayList<>();

        ChatRecordModel test1 = new ChatRecordModel();
        Set<String> keyWord = new HashSet<>();
        keyWord.add("你好");
        test1.setKeyWord(keyWord);
        test1.setReply("你好呀");

        ChatRecordModel test2 = new ChatRecordModel();
        Set<String> keyWord2 = new HashSet<>();
        keyWord2.add("你");
        keyWord2.add("名字");
        test2.setKeyWord(keyWord2);
        test2.setReply("我叫XXX");

        recordList.add(test1);
        recordList.add(test2);
        result.setRecordList(recordList);
        return result;
    }

    //本地测试
    public static void main(String[] args) throws JsonProcessingException {
        String test1 = "你好我是";
        String test2 = "你";
        String test3 = "好2我";
        System.out.println(test1.contains(test2));
        System.out.println(test1.contains(test3));
    }
}
