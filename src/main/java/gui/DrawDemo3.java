package gui;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.*;

// 绘制日历
public class DrawDemo3 extends JPanel {
    public DrawDemo3() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 设置第一行
        g.drawString("星期日",0,10);
        g.drawString("星期一",50,10);
        g.drawString("星期二",100,10);
        g.drawString("星期三",150,10);
        g.drawString("星期四",200,10);
        g.drawString("星期五",250,10);
        g.drawString("星期六",300,10);

        // 获取当前月的起始日期
        // 创建一个 LocalDate 对象，表示 2023 年 11 月 1 日
        LocalDate date = LocalDate.of(2023, 11, 1);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(dayOfWeek);

        int c=0;
        switch (dayOfWeek){
            case SUNDAY:
                c = 0;
                break;
            case MONDAY:
                c = 50;
                break;
            case TUESDAY:
                c=100;
                break;
            case WEDNESDAY:
                c=150;
                break;
            case THURSDAY:
                c=200;
                break;
            case FRIDAY:
                c=250;
                break;
            case SATURDAY:
                c=300;
                break;
        }


        int a=c,b=25;
        for(int i=1;i<32;i++){
            g.drawString(Integer.toString(i),a,b);
            if(a>=300){
                b+=20;
                a=0;
            }else {
                a+=50;
            }
        }

        g.drawString("这是2023年11月",0,200);
    }



    public static void main(String[] args) {
        JFrame w=new JFrame();
        w.setVisible(true);
        w.setContentPane(new DrawDemo3());
        w.setLocationRelativeTo(null);
        w.setSize(500, 500);
//        w.setLayout(new BorderLayout());
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

