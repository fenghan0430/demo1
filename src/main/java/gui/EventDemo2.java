package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;
import javax.swing.*;
public class EventDemo2 extends JFrame{
    public EventDemo2() {
        // 设置窗口
        this.setVisible(true);
        setLocationRelativeTo(null);
        setSize(500, 500);
        this.setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p1 = new JPanel();
        JRadioButton radioButton1 = new JRadioButton("男");
        JRadioButton radioButton2 = new JRadioButton("女");
        ButtonGroup Group = new ButtonGroup();
        JComboBox<String> comboBox = new JComboBox<String>();
        String[] men={"张三","李四"};
        String[] women={"如花","李婶"};

        Group.add(radioButton1);Group.add(radioButton2);
        comboBox.addItem("先选择一个性别");
        p1.add(comboBox);
        p1.add(radioButton1);p1.add(radioButton2);
        this.add("North", p1);

        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox.removeAllItems();
                comboBox.addItem("张三");
                comboBox.addItem("李四");
            }
        });
        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBox.removeAllItems();
                comboBox.addItem("如花");
                comboBox.addItem("李婶");
            }
        });
    }
    public static void main(String[] args) {
    new EventDemo2();
    }
}
