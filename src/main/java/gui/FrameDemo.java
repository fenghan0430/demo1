package gui;

import java.awt.*;
import javax.swing.*;

public class FrameDemo extends JFrame {

    JButton b[];
    public FrameDemo(){
        b=new JButton[10];
        for(int i=0;i<b.length;i++){
            b[i]=new JButton("Button " + i);
            this.add(b[i]);
        }
        Container c=this.getContentPane();
        // 设置背景颜色
        this.setBackground(Color.pink);
        // 设置窗口大小
        this.setSize(800,600);
        // 设置窗口的图标
        ImageIcon icon = new ImageIcon("images\\icon.png");
        this.setIconImage(icon.getImage());
        // 设置窗口的标题
        this.setTitle("Java GUI");
        // 让窗口居中
        setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        FrameDemo f = new FrameDemo();

    }
}
