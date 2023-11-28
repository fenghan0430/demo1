package gui;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

// 画条形图
public class DrawDemo2 extends JPanel {
    public DrawDemo2() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                repaint();
            }
        });
    }

    int startx=50,starty=50;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 画x,y轴
        int w=getWidth();
        int h=getHeight();
        g.drawLine(startx,starty,startx,h-50);
        g.drawLine(startx,h-50,w-50,h-50);

        // 画柱状图
        g.setColor(Color.red);
        g.fillRect(startx+20,h-50-114,30,114);
    }


    public static void main(String[] args) {
        JFrame w=new JFrame();
        w.setVisible(true);
        w.setContentPane(new DrawDemo2());
        w.setLocationRelativeTo(null);
        w.setSize(500, 500);
//        w.setLayout(new BorderLayout());
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
