package gui;

import java.awt.*;
import javax.swing.*;

public class BoxDemo extends JFrame {
    Button b1,b2,b3,b4;
    public BoxDemo(){
        b1=new Button("one");
        b2=new Button("two");
        b3=new Button("two add one");
        b4=new Button("two add two");

        //2.创建一个横向的Box,并添加两个按钮
        Box h1 = Box.createHorizontalBox();
        h1.add(b1);
        h1.add(b2);

        //3.创建一个纵向的Box，并添加两个按钮
        Box h2 = Box.createVerticalBox();
        h2.add(h1);
        h2.add(b3);
        h2.add(b4);

        //4.把box容器添加到frame容器中
        this.add(h2);

        //5.设置frame最佳大小并可见
        this.pack();
        this.setVisible(true);

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
//        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new BoxDemo();
    }
}



