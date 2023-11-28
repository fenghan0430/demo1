package Thread;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class WeatherApp {

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
    public static void main(String[] args) {
        // 创建主窗口
        JFrame frame = new JFrame("Weather Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        // 创建下拉框
        JComboBox<String> cityComboBox = new JComboBox<>(new String[]{"北京", "深圳", "上海", "长沙"});
        JPanel topPanel = new JPanel();
        topPanel.add(cityComboBox);

        // 创建搜索按钮
        JButton searchButton = new JButton("查询");
        topPanel.add(searchButton);

        // 创建文本区域显示天气信息
        JTextArea weatherInfoArea = new JTextArea("天气信息将会显示在这里。");
        weatherInfoArea.setEditable(false);

        // 将组件添加到窗口
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(weatherInfoArea), BorderLayout.CENTER);

        //初始化api
        WeatherApp tp=new WeatherApp();

        // 按钮事件监听
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCity = (String) cityComboBox.getSelectedItem();
                // TODO: 根据选中的城市获取天气信息
                JSONObject api_request = tp.useapi(selectedCity);
                weatherInfoArea.setText(
                    "查询城市：" + selectedCity + "\n" +
                    "温度：" + api_request.getString("day_air_temperature") + "\n" +
                    "天气：" + api_request.getString("day_weather") + "\n" +
                    "风力：" + api_request.getString("day_wind_power") + "\n" +
                    "风向：" + api_request.getString("day_wind_direction")
                );
            }
        });

        // 显示窗口
        frame.setVisible(true);
    }
}


