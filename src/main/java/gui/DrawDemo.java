package gui;

import javax.swing.*;
import java.awt.*;
// 画一个普通的圆
public class DrawDemo extends JPanel {
    public DrawDemo() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(10, 10, 100, 100);
    }

    public static void main(String[] args) {
        JFrame w=new JFrame();
        w.setVisible(true);
        w.setContentPane(new DrawDemo());
        w.setLocationRelativeTo(null);
        w.setSize(500, 500);
        w.setLayout(new BorderLayout());
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
