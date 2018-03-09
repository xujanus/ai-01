package com.xujun365.order.list;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xujun365.model.ChatDataBaseModel;
import com.xujun365.model.ChatRecordModel;
import com.xujun365.order.Order;
import com.xujun365.utils.FileUtil;
import com.xujun365.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class ChatOrder extends Order {
    private static final String NO_LAST_SENTENCE_REPLAY = "你好，我是AI-01，很高兴能和你聊天~";
    private static final String CAN_NO_ANALYSIS_SENTENCE_REPLAY = "抱歉，我不知道该怎么回答你哦";
    private static final String LOCAL_DB_PATH = "E:\\github\\local_db\\chatDB.json";
    private static final String STUDY_ORDER_WORD = "学习：";
    private static final String STUDY_ORDER_SUCCESS = "学习成功！";
    private static final String WEATHER_REPORT_ORDER_WORD = "的天气";
    //聊天数据缓存
    private static ChatDataBaseModel chatDataCache;
    String lastSentence;

    public ChatOrder() {
        this.lastSentence = null;
    }

    public ChatOrder(String lastSentence) {
        this.lastSentence = lastSentence;
    }

    @Override
    public void executeOrder() {
        //无输入的情况
        if (lastSentence == null || "".equals(lastSentence)) {
            System.out.println(NO_LAST_SENTENCE_REPLAY);
            return;
        }

        //过滤学习关键字，触发学习模式
        if (checkIsStudyOrder(lastSentence)) {
            doStudySentence(lastSentence);
            return;
        }

        //过滤天气关键字，触发天气预报模式
        if (checkIsWeatherReportOrder(lastSentence)) {
            doWeatherReportOrder(lastSentence);
            return;
        }

        //聊天程序
        System.out.println(reply(lastSentence));
    }

    //执行当前天气预报命令
    private void doWeatherReportOrder(String lastSentence) {
        String text = lastSentence.substring(0, lastSentence.lastIndexOf(WEATHER_REPORT_ORDER_WORD));
        new WeatherOrder(text).executeOrder();
    }

    //学习当前语句
    private void doStudySentence(String lastSentence) {
        String text = lastSentence.substring(STUDY_ORDER_WORD.length());
        String[] words = text.trim().split(" ");

        //解析语句，生成关键字和回话
        Set<String> keyWord = new HashSet<>();
        String reply = null;
        for (int i = 0; i < words.length; i++) {
            if (i == (words.length - 1)) {
                reply = words[i];
            } else {
                keyWord.add(words[i]);
            }
        }

        ChatDataBaseModel chatDataBaseModel = getChatDataBaseModel();
        List<ChatRecordModel> recordList = chatDataBaseModel.getRecordList();
        ChatRecordModel record = getRecordByKeyWord(recordList, keyWord);
        if (record == null) {
            record = new ChatRecordModel();
            record.setKeyWord(keyWord);
            record.setReply(reply);
            recordList.add(record);
        } else {
            record.setReply(reply);
        }

        saveChatDataBaseModel();

        System.out.println(STUDY_ORDER_SUCCESS);
    }

    //根据关键字搜索对应的数据记录
    private ChatRecordModel getRecordByKeyWord(List<ChatRecordModel> recordList, Set<String> keyWord) {
        for (ChatRecordModel temp : recordList) {
            Set<String> keyWord1 = temp.getKeyWord();
            if (keyWord.equals(keyWord1)) return temp;
        }
        return null;
    }

    //判断当前句子是否是学习口令
    private static boolean checkIsStudyOrder(String lastSentence) {
        if (lastSentence.startsWith(STUDY_ORDER_WORD)) {
            String text = lastSentence.substring(STUDY_ORDER_WORD.length());
            String[] words = text.trim().split(" ");
            if (words.length > 1) {
                return true;
            }
        }
        return false;
    }

    //判断当前句子是否是天气预报口令
    private boolean checkIsWeatherReportOrder(String lastSentence) {
        //关键词存在，并且不是第一个词
        return lastSentence.lastIndexOf(WEATHER_REPORT_ORDER_WORD) >= 0 && lastSentence.length() > WEATHER_REPORT_ORDER_WORD.length();
    }

    //回话
    private String reply(String lastSentence) {
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
        for (ChatRecordModel record : recordList) {
            Set<String> keyWord = record.getKeyWord();
            if (wordMatchSentence(keyWord, lastSentence)) {
                result = record.getReply();
                break;
            }

        }

        return result;
    }

    //检查词组是否与语句匹配
    private boolean wordMatchSentence(Set<String> keyWord, String lastSentence) {
        for (String word : keyWord) {
            if (!lastSentence.contains(word)) {
                return false;
            }
        }
        return true;
    }

    //获取聊天资料库
    private ChatDataBaseModel getChatDataBaseModel() {
        //如果聊天数据未填充
        if (chatDataCache == null) {
            String chatDataJson = FileUtil.readFile(LOCAL_DB_PATH);
            chatDataCache = JsonUtil.jsonToPojo(chatDataJson, ChatDataBaseModel.class);
        }

        return chatDataCache;
    }

    //保存缓存数据到本地DB
    public void saveChatDataBaseModel() {
        if (chatDataCache != null) {
            String json_str = JsonUtil.pojoToJson(chatDataCache);
            FileUtil.writeFile(LOCAL_DB_PATH, json_str);
            log.info("缓存数据保存到本地成功");
        } else {
            log.info("缓存数据不存在，无法保存到本地！");
        }
    }

    //本地测试
    public static void main(String[] args) throws JsonProcessingException {
        log.info("测试日志记录-info2");
        log.debug("测试日志记录-debug2");
        System.out.println(checkIsStudyOrder("学习： 你 的 "));
    }
}
