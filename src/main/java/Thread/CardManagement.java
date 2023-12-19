package Thread;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.*;

public class CardManagement extends JFrame {
    private String url = "jdbc:mysql://10.1.78.81:3306/schoo1"; // MySQL数据库地址和端口
    private String username = "root"; // 数据库用户名
    private String password = "123456"; // 数据库密码
    public CardManagement() {
        setTitle("联系");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400)); // 设置推荐的窗口大小

        // 使用GridBagLayout布局管理器
        GridBagLayout layout = new GridBagLayout();
        getContentPane().setLayout(layout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // 设置组件间的间隔

        // 添加搜索框和搜索按钮在最上方
        JTextField searchField = new JTextField(20);
        gbc.gridx = 0; // 列
        gbc.gridy = 0; // 行
        gbc.gridwidth = 2;
        getContentPane().add(searchField, gbc);

        JButton searchButton = new JButton("搜索");
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        getContentPane().add(searchButton, gbc);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里编写添加按钮的操作逻辑
                // 例如，可以从文本框中获取数据，然后将数据添加到列表中
                // 更新已保存的条目列表等
            }
        });

        // 创建标签和文本框
        String[] labels = {"姓名", "性别", "出生地", "电话", "手机", "QQ", "微信"};
        JTextField[] textFields = new JTextField[labels.length];
        gbc.gridwidth = 1;

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; // 第一列
            gbc.gridy = i + 1; // 从第二行开始，因为第一行是搜索框
            getContentPane().add(new JLabel(labels[i]), gbc);

            textFields[i] = new JTextField(10);
            gbc.gridx = 1; // 第二列
            gbc.gridwidth = 2; // 占据两个单元格宽度
            getContentPane().add(textFields[i], gbc);
            gbc.gridwidth = 1; // 重置回默认值
        }

        // 添加按钮
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        // 创建添加、保存和删除按钮
        JButton addButton = new JButton("添加");
        JButton saveButton = new JButton("保存");
        JButton deleteButton = new JButton("删除");

        // 为添加按钮添加监听器
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // 为保存按钮添加监听器
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里编写保存按钮的操作逻辑
                // 例如，可以从文本框中获取数据，然后更新已保存的条目等
            }
        });

        // 为删除按钮添加监听器
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里编写删除按钮的操作逻辑
                // 例如，可以从文本框中获取数据，然后从已保存的条目中删除等
            }
        });

        // 将按钮添加到按钮面板
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);

        // 将按钮面板添加到主窗口
        gbc.gridx = 0;
        gbc.gridy = labels.length + 1; // 在标签和文本框之后
        gbc.gridwidth = 3;
        getContentPane().add(buttonPanel, gbc);

        // 用于显示已保存条目的列表
        JList<String> savedList = new JList<>(getAllNames());
        savedList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // 防止事件被调用两次
                    String selectedName = savedList.getSelectedValue();
                    if (selectedName != null) {
                        // 调用您的方法来获取人员信息
                        String[] personInfo = getPersonInfoByName(selectedName);
                        for (int i = 0; i < personInfo.length && i < textFields.length; i++) {
                            textFields[i].setText(personInfo[i]);
                        }
                    }
                }
            }
        });
        JScrollPane listScroller = new JScrollPane(savedList);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = GridBagConstraints.REMAINDER; // 占据剩余的所有行
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;  // 给予列表额外的水平空间
        gbc.weighty = 1.0;  // 给予列表额外的垂直空间
        getContentPane().add(listScroller, gbc);

        pack(); // 调整窗口大小以适应组件
    }

    public DefaultListModel<String> getAllNames() {
        DefaultListModel<String> nameListModel = new DefaultListModel<>();

        try {
            // 注册MySQL JDBC驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

            // 创建SQL查询
            PreparedStatement statement = connection.prepareStatement("SELECT name FROM person_info");

            // 执行查询
            ResultSet resultSet = statement.executeQuery();

            // 处理查询结果
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                nameListModel.addElement(name);
            }

            // 关闭连接
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println(nameListModel);
        return nameListModel;
    }

    public String[] getPersonInfoByName(String name) {
        String[] personInfo = new String[7]; // 因为有8列信息

        try {
            // 注册MySQL JDBC驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

            // 创建SQL查询
            String sql = "SELECT name,gender,birthplace,phone,mobile,qq,wechat FROM person_info WHERE name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);

            // 执行查询
            ResultSet resultSet = statement.executeQuery();

            // 处理查询结果
            if (resultSet.next()) {
                personInfo[0] = resultSet.getString("name");
                personInfo[1] = resultSet.getString("gender");
                personInfo[2] = resultSet.getString("birthplace");
                personInfo[3] = resultSet.getString("phone");
                personInfo[4] = resultSet.getString("mobile");
                personInfo[5] = resultSet.getString("qq");
                personInfo[6] = resultSet.getString("wechat");
            }

            // 关闭连接
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return personInfo;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CardManagement mainFrame = new CardManagement();
            mainFrame.setVisible(true);
        });
    }
}
