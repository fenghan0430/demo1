package Thread;

import gui.DrawDemo3;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class WeatherPanl extends JFrame{
    public WeatherPanl() {
        // 设置窗口标题和大小
        setTitle("天气预报");
        setSize(900, 500);

        // 创建组件
        JComboBox<String> cityComboBox = new JComboBox<>();
        JButton showButton = new JButton("搜索");
        JPanel weatherpanel = new JPanel();

        // 添加组件到窗口中
        JPanel panel = new JPanel();
        panel.add(cityComboBox);
        panel.add(showButton);
        add(panel, BorderLayout.NORTH);
        add(weatherpanel, BorderLayout.CENTER);

        // 为按钮添加监听器
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 获取所选城市的名称
                String city = (String) cityComboBox.getSelectedItem();
                // 获取该城市的天气预报
                String weather = "25℃";
                // 在标签中显示结果

            }
        });
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

    public static void main(String[] args) {
    }
}
