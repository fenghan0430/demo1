package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventDemo extends JPanel {
    JButton b1;
    Font h1=new Font("微软雅黑", Font.BOLD, 24);
    public EventDemo() {
        b1=new JButton("单机我");
        b1.setPreferredSize(new Dimension(200,50));
        b1.setFont(h1);b1.setForeground(Color.white);
        b1.setBackground(Color .darkGray);
        this.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "我知道你单击了我");
            }});

    }
    public static void main(String args[]){
        JFrame window=new JFrame("节日提醒");
        window.setContentPane(new EventDemo());
        window.setSize(300,200);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
