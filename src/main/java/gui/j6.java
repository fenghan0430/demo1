
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class j6 extends JFrame{
    private final JComboBox<String> cityComboBox;
    private final JLabel resultLabel;

    public j6() {
        // 设置窗口标题和大小
        setTitle("天气预报");
        setSize(400, 200);

        // 创建组件
        cityComboBox = new JComboBox<>();
        JButton showButton = new JButton("搜索");
        resultLabel = new JLabel();

        // 添加组件到窗口中


        JPanel panel = new JPanel();
        panel.add(cityComboBox);
        panel.add(showButton);
        add(panel, BorderLayout.NORTH);
        add(resultLabel, BorderLayout.CENTER);

        // 读取属性文件并将城市名称添加到组合框中
        Properties props = new Properties();
        try {
            props.load(Files.newInputStream(Paths.get("hobby.properties")));
            for (String city : props.stringPropertyNames()) {
                cityComboBox.addItem(city);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // 为按钮添加监听器
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 获取所选城市的名称
                String city = (String) cityComboBox.getSelectedItem();
                // 获取该城市的天气预报
                String weather = "25℃";
                // 在标签中显示结果
                resultLabel.setText(city + "的天气预报：" + weather);
            }
        });
    }

    public static void main(String[] args) {
        j6 app = new j6();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}