package gui;

import java.awt.*;
import javax.swing.*;
public class JCombDemo extends JFrame {
    // 定义一个字符串数组，用于存放 hobby 对象
    String hobbys[] = {"Coding", "Sleeping", "Eating", "Playing"};
    // 创建一个 DefaultComboBoxModel 对象，用于存放 hobby 对象
    DefaultComboBoxModel<hobby> data=new DefaultComboBoxModel<hobby>();
    // 创建一个 JComboBox 对象，用于显示 hobby 对象
    JComboBox<DefaultComboBoxModel<hobby>> com=new JComboBox(data);
    public JCombDemo() {
        // 调用 refresh 方法，添加 hobby 对象
        refresh();
        // 设置窗口可见
        this.setVisible(true);
        // 设置窗口大小
        setSize(800, 800);
        // 设置窗口位置
        setLocationRelativeTo(null);
    }
    // 添加 hobby 对象
    public void refresh(){
        for(int i=0;i<hobbys.length;i++){
            data.addElement(new hobby(i, hobbys[i]));
        }
    }

}