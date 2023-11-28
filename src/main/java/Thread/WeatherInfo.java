package Thread;

import gui.DrawDemo3;

import javax.swing.*;
import java.awt.*;

public class WeatherInfo extends JPanel {
    public WeatherInfo() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("当前城市" + "深圳", 100, 100);
        g.drawString("温度城市" + "深圳", 100, 120);
        g.drawString("天气" + "深圳", 100, 140);
        g.drawString("风力" + "深圳", 100, 160);
        g.drawString("风向" + "深圳", 100, 180);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Weather Info");
        frame.setContentPane(new WeatherInfo());
        frame.setSize(900, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
