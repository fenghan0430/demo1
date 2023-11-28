package Thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherApp {

    public static void main(String[] args) {
        // 创建主窗口
        JFrame frame = new JFrame("Weather Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        // 创建下拉框
        JComboBox<String> cityComboBox = new JComboBox<>(new String[]{"New York", "London", "Tokyo", "Paris"});
        JPanel topPanel = new JPanel();
        topPanel.add(cityComboBox);

        // 创建搜索按钮
        JButton searchButton = new JButton("Search");
        topPanel.add(searchButton);

        // 创建文本区域显示天气信息
        JTextArea weatherInfoArea = new JTextArea("Weather information will be displayed here.");
        weatherInfoArea.setEditable(false);

        // 将组件添加到窗口
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(weatherInfoArea), BorderLayout.CENTER);

        // 按钮事件监听
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCity = (String) cityComboBox.getSelectedItem();
                // TODO: 根据选中的城市获取天气信息
                weatherInfoArea.setText("Fetching weather for " + selectedCity + "...");
                // 这里应该调用天气API并更新weatherInfoArea
            }
        });

        // 显示窗口
        frame.setVisible(true);
    }
}


