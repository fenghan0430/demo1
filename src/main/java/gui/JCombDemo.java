package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;
import javax.swing.*;
public class JCombDemo extends JFrame {
    DefaultComboBoxModel<hobby> data=new DefaultComboBoxModel<hobby>();
    JComboBox<DefaultComboBoxModel<hobby>> com=new JComboBox(data);
    JButton button=new JButton("搜索");
    public JCombDemo() throws FileNotFoundException {
        // 向data里添加城市数据
        refresh();

        // 设置布局
        JPanel p1 = new JPanel();
        p1.add(com);
        p1.add(button);
        p1.setBackground(Color.yellow);
        add(p1, BorderLayout.NORTH);

        // 设置窗口
        this.setVisible(true);
        setSize(500, 500);
        setLocationRelativeTo(null);

        // 添加监听器
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, com.getSelectedItem());
            }});

    }
    public void refresh(){
        Properties pro=new Properties();
        BufferedReader reader= null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("hobby.properties")
                    )
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            pro.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String key:pro.stringPropertyNames()){
            System.out.println(key);
            data.addElement(new hobby(key, pro.getProperty(key)));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new JCombDemo();
    }

}