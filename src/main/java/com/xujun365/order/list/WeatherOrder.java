package com.xujun365.order.list;

import com.xujun365.order.Order;
import com.xujun365.utils.HttpUtils;
import com.xujun365.utils.JsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>ClassName:     WeatherOrder
 * <p>Description:   天气命令的实现类
 * <p>Author         xujun
 * <p>Version        V1.0
 * <p>Date           2018/2/25
 */
public class WeatherOrder extends Order {
    //查询地点，默认为杭州
    String area = "杭州";
    private static final String ERROR_MESSAGE = "天气查询失败，请检联系技术人员";

    public WeatherOrder() {
    }

    public WeatherOrder(String area) {
        this.area = area;
    }

    @Override
    public void executeOrder() {
        String result = queryWeatherReport(area);
        System.out.println(result);
    }

    private String queryWeatherReport(String area) {
        String lineSeparator = System.getProperty("line.separator", "\n");
        String result = area + "地区" + lineSeparator;
        Map result_map = getInfoFromAPI(area);
        if (result_map == null) return ERROR_MESSAGE;

        Map showapi_res_body = (Map) result_map.get("showapi_res_body");

        Map now = (Map) showapi_res_body.get("now");

        if (now == null) return ERROR_MESSAGE;

        Map f1 = (Map) showapi_res_body.get("f1");
        Map f2 = (Map) showapi_res_body.get("f2");

        String now_temp = now.get("temperature").toString();
        result += "当前气温：" + now_temp + "°" + lineSeparator;
        String today_day_temp = f1.get("day_air_temperature").toString();
        result += "今天白天温度：" + today_day_temp + "°" + lineSeparator;
        String today_night_temp = f1.get("night_air_temperature").toString();
        result += "今天夜里温度：" + today_night_temp + "°" + lineSeparator;
        String today_weather = f1.get("day_weather").toString();
        result += "今天天气：" + today_weather + lineSeparator;
        String tomorrow_day_temp = f2.get("day_air_temperature").toString();
        result += "明天白天温度：" + tomorrow_day_temp + "°" + lineSeparator;
        String tomorrow_night_temp = f2.get("night_air_temperature").toString();
        result += "明天夜里温度：" + tomorrow_night_temp + "°" + lineSeparator;
        String tomorrow_weather = f1.get("day_weather").toString();
        result += "明天天气：" + tomorrow_weather + lineSeparator;

        return result;
    }

    //从阿里云的天气API获取天气信息：https://market.aliyun.com/products/57096001/cmapi010812.html
    private Map getInfoFromAPI(String area) {
        Map result = null;
        String host = "https://ali-weather.showapi.com";
        String path = "/spot-to-weather";
        String method = "GET";
        String appCode = "03dae5f56d914e2999a0625d6f263e94";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appCode);
        Map<String, String> query = new HashMap<>();
        query.put("area", area);
        //是否需要其它信息，0表示关闭
        query.put("need3HourForcast", "0");
        query.put("needAlarm", "0");
        query.put("needHourData", "0");
        query.put("needIndex", "0");
        query.put("needMoreDay", "0");

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, query);
//            System.out.println(response.toString());
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            result = JsonUtil.jsonToPojo(responseString, Map.class);
//            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        WeatherOrder test = new WeatherOrder();
        test.executeOrder();


    }
}
