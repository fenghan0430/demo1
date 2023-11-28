package Thread;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.util.*;
import org.json.*;

public class apitest {
    public static void main(String[] args) {
        apitest temp=new apitest();

        String city = "香港"; // 指定查询的城市

        JSONObject f1 = temp.useapi(city); // 调用api，返回json字符串

        // 得到今天的气温，天气，日出日落
        System.out.println("你查询的城市是" + city);
        System.out.println("气温是" + f1.getString("day_air_temperature") + "摄氏度");
        System.out.println("天气是" + f1.getString("day_weather"));
        System.out.println("日出日落时间是：" + f1.getString("sun_begin_end"));
    }

    public JSONObject useapi(String area){
        // 这个方法用来调用api，返回一个json字符串

        String host = "https://ali-weather.showapi.com";
        String path = "/area-to-weather-date";
        String method = "GET";
        String appcode = "2d3355bb56d4427d9fa61a739f473a70";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("area", area);
        querys.put("need3HourForcast", "0");

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            JSONObject obj = new JSONObject(EntityUtils.toString(response.getEntity()));
            return (obj.getJSONObject("showapi_res_body")).getJSONObject("f1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
