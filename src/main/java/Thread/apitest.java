package Thread;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.util.*;
import org.json.*;

public class apitest {
    public static void main(String[] args) {
        apitest temp=new apitest();

        String city = "香港"; // 指定查询的城市

        JSONObject api_response = temp.Deal_accordingly(temp.useapi(city)); // 调用api，返回json字符串

        // 得到深圳今天的气温，天气，日出日落
        JSONObject f1 = api_response.getJSONObject("f1");
        System.out.println("你查询的城市是" + city);
        System.out.println("气温是" + f1.getString("day_air_temperature") + "摄氏度");
        System.out.println("天气是" + f1.getString("day_weather"));
        System.out.println("日出日落时间是：" + f1.getString("sun_begin_end"));

    }

    public String useapi(String area){
        // 这个方法用来调用api，返回一个json字符串

        String host = "https://ali-weather.showapi.com";
        String path = "/area-to-weather-date";
        String method = "GET";
        String appcode = "2d3355bb56d4427d9fa61a739f473a70";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("area", area);
//        querys.put("date", "20200319");
        querys.put("need3HourForcast", "0");

        String i1 = "";

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //判断是否成功接收
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                return "ERROR[Code " + statusCode + "]";
            }

            //获取response的body
            i1 = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i1;
    }

    public JSONObject Deal_accordingly(String jsonResponse){
        // 这个方法用来处理api返回的json数据，让他返回我们想要的值
        JSONObject obj = new JSONObject(jsonResponse);
        // 获取 showapi_res_body 中的内容
        JSONObject showapiResBody = obj.getJSONObject("showapi_res_body");
        return showapiResBody;
    }
}
