package gui;

import java.awt.*;
import javax.swing.*;

public class tp_test_gui extends JFrame {

    String img="images\\test1.png";
    String img2="images\\test2.png";
    public tp_test_gui() {
        // 创建一个垂直盒式布局包裹所有布局
        Box root_win = Box.createVerticalBox();

        // 首先，做一个横向盒式布局，其中放两个JLabel，用来放置两张图片
        Box one_win = Box.createHorizontalBox();

        JLabel l_pictrue = new JLabel();
        ImageIcon imageIcon1 = new ImageIcon(img); // 替换为第一张图片的路径
        l_pictrue.setIcon(imageIcon1);
        l_pictrue.setPreferredSize(new Dimension(300, 300));
        one_win.add(l_pictrue);

        JLabel r_pictrue = new JLabel();
        ImageIcon imageIcon2 = new ImageIcon(img); // 替换为第二张图片的路径
        r_pictrue.setIcon(imageIcon2);
        l_pictrue.setPreferredSize(new Dimension(300, 300));
        one_win.add(r_pictrue);

        // 将第一个横向盒式布局添加到垂直盒式布局中
        root_win.add(one_win);

        // 第二部分，横向盒式布局包裹多个纵向盒式布局
        Box his_box = Box.createHorizontalBox();

        Box[] his_sonArray = new Box[6]; // 创建一个包含6个his_son对象的数组

        for (int i = 0; i < 6; i++) {
            his_sonArray[i] = Box.createVerticalBox();

            ImageIcon imageIconx = new ImageIcon(img2);
            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(100, 150));
            JLabel textArea = new JLabel("温度正常");
            textArea.setPreferredSize(new Dimension(100, 50));
            label.setIcon(imageIconx);

            // 将JLabel和文本区域添加到纵向盒式布局中
            his_sonArray[i].add(label);
            his_sonArray[i].add(textArea);

            // 将纵向盒式布局添加到横向盒式布局中
            his_box.add(his_sonArray[i]);
        }

        root_win.add(his_box);

        // 将垂直盒式布局添加到窗口中
        getContentPane().add(root_win);

        setTitle("温度检测程序");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(605, 505);
        setLocationRelativeTo(null);

        // 设置窗口不可缩放
        setResizable(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            tp_test_gui gui = new tp_test_gui();
            gui.setVisible(true);
        });
    }
}
