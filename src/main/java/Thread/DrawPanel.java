package Thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DrawPanel extends JFrame {

    int w=500,h=500,x=50,y=150;
    Font font=new Font("黑体",Font.BOLD,150);

    private int secondsLeft = 60; // 初始倒计时秒数
    private Timer timer;

    public DrawPanel(){
        this.setPreferredSize(new Dimension(w,h));//设置初始尺寸
//        this.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                repaint();
//            }
//        });
        setTitle("Countdown Timer");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        new Thread(){
            @Override
            public void run() {
                while (secondsLeft > 0) {
                    try {
                        secondsLeft--;
                        repaint(); // 重新绘制界面
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

//        Thread timerThread = new Thread(() -> {
//            // 创建定时器，每秒触发一次
//            timer = new Timer(1000, new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    if (secondsLeft > 0) {
//                        secondsLeft--;
//                        repaint(); // 重新绘制界面
//                    } else {
//                        timer.stop(); // 如果倒计时结束，停止定时器
//                    }
//                }
//            });
//            timer.start(); // 启动定时器
//        });

//        timerThread.start();


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setFont(font);

        g.setColor(Color.ORANGE);
        g.fillOval(80, 80, 300, 300);

        g.setColor(Color.GRAY);
        g.fillOval(85, 85, 290, 290);

        g.setColor(Color.ORANGE);
        g.fillArc(105, 105, 250, 250, 90, (int) (360/60*secondsLeft));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DrawPanel());
    }
}
