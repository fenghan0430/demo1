package gui;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.awt.*;
import javax.swing.*;

public class LoginDemo extends JFrame {
    Panel p1,p2;
    JLabel title,l1,l2;
    JTextField t1;
    JButton b1,b2;
    JPasswordField t2;
    public LoginDemo() {
        p1=new Panel();p2=new Panel();
        title=new JLabel("登录窗口");l1=new JLabel("请输入姓名");l2 =new JLabel("请输入密码");
        t1=new JTextField();t2=new JPasswordField();
        b1=new JButton("提交");b2=new JButton("取消");

        p1.setLayout(new GridLayout(2,2));
        p1.add(l1);p1.add(t1);p1.add(l2);p1.add(t2);

        p2.setLayout(new FlowLayout());
        p2.add(b1);p2.add(b2);

        this.setLayout(new BorderLayout());
        Container c=this.getContentPane();
        c.add("North",title);
        c.add("Center",p1);
        c.add("South",p2);
        this.pack();
        this.setTitle("Login");
        this.setVisible(true);
        this.setLocationRelativeTo(null);


    }

    public static void main(String[] args) {
        new LoginDemo();
    }
}
