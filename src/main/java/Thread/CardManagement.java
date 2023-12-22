package Thread;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.*;

public class CardManagement extends JFrame {
    private final String url = "jdbc:mysql://10.1.78.81:3306/schoo1"; // MySQL数据库地址和端口
    private final String username = "root"; // 数据库用户名
    private final String password = "123456"; // 数据库密码
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
        String[] labels = {"id", "姓名", "性别", "出生地", "电话", "手机", "QQ", "微信"};
        JTextField[] textFields = new JTextField[labels.length];
        gbc.gridwidth = 1;

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; // 第一列
            gbc.gridy = i + 1; // 从第二行开始，因为第一行是搜索框
            getContentPane().add(new JLabel(labels[i]), gbc);

            textFields[i] = new JTextField(10);
            if (i == 0) { // 如果是ID框
                textFields[i].setEditable(false); // 设置为不可编辑
            }
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
        JButton refreshButton = new JButton("清除并刷新");

        // 将按钮添加到按钮面板
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        // 将按钮面板添加到主窗口
        gbc.gridx = 0;
        gbc.gridy = labels.length + 1; // 在标签和文本框之后
        gbc.gridwidth = 3;
        getContentPane().add(buttonPanel, gbc);

        // 用于显示已保存条目的列表
        JList<String> savedList = new JList<>(getAllNamesAndIds());
        savedList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // 防止事件被调用两次
                    String selectedNameId = savedList.getSelectedValue();
                    if (selectedNameId != null) {
                        // 从选中的字符串中提取名字
                        String selectedId = selectedNameId.substring(0, selectedNameId.indexOf(": "));

                        // 调用修改后的方法来获取人员信息，此时传入的是ID
                        String[] personInfo = getPersonInfoById(selectedId);
                        for (int i = 0; i < 8; i++) {
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

        // 为添加按钮添加监听器
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] textFieldValues = new String[labels.length - 1];

                // 从第二个文本框开始（跳过ID框）
                for (int i = 0; i < textFieldValues.length; i++) {
                    textFieldValues[i] = textFields[i + 1].getText(); // 注意这里的索引是 i + 1
                }

                // 检查是否有空值
                boolean hasEmptyFields = false;
                for (String value : textFieldValues) {
                    if (value == null || value.isEmpty()) {
                        hasEmptyFields = true;
                        break;
                    }
                }

                if (hasEmptyFields) {
                    // 存在空值
                    JOptionPane.showMessageDialog(CardManagement.this, "有一个或多个文本框为空值。", "失败", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    // 所有字段都有值
                    System.out.println("所有文本框都包含有效值。");
                }

                // SQL插入语句
                String sql = "INSERT INTO person_info (name,gender,birthplace,phone,mobile,qq,wechat) VALUES (?, ?, ?, ?, ?, ?, ?)";

                try {
                    // 注册MySQL JDBC驱动程序
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // 建立数据库连接
                    Connection connection = DriverManager.getConnection(url, username, password);

                    // 创建PreparedStatement对象，用于执行SQL语句
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);

                    // 设置参数值
                    preparedStatement.setString(1, textFieldValues[0]);
                    preparedStatement.setString(2, textFieldValues[1]);
                    preparedStatement.setString(3, textFieldValues[2]);
                    preparedStatement.setString(4, textFieldValues[3]);
                    preparedStatement.setString(5, textFieldValues[4]);
                    preparedStatement.setString(6, textFieldValues[5]);
                    preparedStatement.setString(7, textFieldValues[6]);

                    // 执行SQL插入操作
                    int rowsAffected = preparedStatement.executeUpdate();

                    // 输出插入的行数
                    System.out.println("插入了 " + rowsAffected + " 行数据。");

                    // 关闭连接
                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
                for (int i = 0; i < textFields.length; i++) {
                    textFields[i].setText(""); // 清空文本框内容
                }
                JOptionPane.showMessageDialog(CardManagement.this, "数据插入成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                refreshJList(savedList);
            }
        });

        // 为保存按钮添加监听器
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] textFieldValues = new String[labels.length];

                for (int i = 0; i < labels.length; i++) {
                    textFieldValues[i] = textFields[i].getText();
                }

                boolean hasEmptyFields = false;

                for (int i = 1; i < textFieldValues.length; i++) {
                    if (textFieldValues[i] == null || textFieldValues[i].isEmpty()) {
                        hasEmptyFields = true;
                        break; // 如果有任何一个字段为空，就跳出循环
                    }
                }

                if (textFieldValues[0].isEmpty()) {
                    JOptionPane.showMessageDialog(CardManagement.this, "未选择任何记录！", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (hasEmptyFields) {
                    JOptionPane.showMessageDialog(CardManagement.this, "有一个或多个文本框为空值。", "失败", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    // 所有字段都有值
                    System.out.println("所有文本框都包含有效值。");
                }
                // SQL插入语句
                String sql = "UPDATE person_info SET name = ?, gender = ?, birthplace = ?, phone = ?, mobile = ?, qq = ?, wechat = ? WHERE id = ?";

                try {
                    // 注册MySQL JDBC驱动程序
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    // 建立数据库连接
                    Connection connection = DriverManager.getConnection(url, username, password);

                    // 创建PreparedStatement对象，用于执行SQL语句
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);

                    // 设置参数值
                    preparedStatement.setString(1, textFieldValues[1]);
                    preparedStatement.setString(2, textFieldValues[2]);
                    preparedStatement.setString(3, textFieldValues[3]);
                    preparedStatement.setString(4, textFieldValues[4]);
                    preparedStatement.setString(5, textFieldValues[5]);
                    preparedStatement.setString(6, textFieldValues[6]);
                    preparedStatement.setString(7, textFieldValues[7]);
                    preparedStatement.setString(8, textFieldValues[0]);
                    // 执行SQL插入操作
                    int rowsAffected = preparedStatement.executeUpdate();

                    // 输出插入的行数
                    System.out.println("更新了 " + rowsAffected + " 行数据。");

                    // 关闭连接
                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
                for (int i = 0; i < textFields.length; i++) {
                    textFields[i].setText(""); // 清空文本框内容
                }
                JOptionPane.showMessageDialog(CardManagement.this, "数据插入成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                refreshJList(savedList);
            }
        });

        // 为删除按钮添加监听器
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textFields[0].getText(); // 获取要删除的记录的ID
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(CardManagement.this, "未选择任何记录！", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(CardManagement.this, "确定要删除这条记录吗？", "确认删除", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // SQL删除语句
                    String sql = "DELETE FROM person_info WHERE id = ?";

                    try {
                        // 注册MySQL JDBC驱动程序
                        Class.forName("com.mysql.cj.jdbc.Driver");

                        // 建立数据库连接
                        Connection connection = DriverManager.getConnection(url, username, password);

                        // 创建PreparedStatement对象
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);

                        // 设置参数值
                        preparedStatement.setInt(1, Integer.parseInt(id)); // 设置ID

                        // 执行SQL删除操作
                        int rowsAffected = preparedStatement.executeUpdate();

                        // 输出删除的行数
                        System.out.println("删除了 " + rowsAffected + " 行数据。");

                        // 关闭连接
                        preparedStatement.close();
                        connection.close();
                    } catch (ClassNotFoundException | SQLException e1) {
                        e1.printStackTrace();
                    }

                    // 清空所有文本框
                    for (JTextField textField : textFields) {
                        textField.setText("");
                    }
                    JOptionPane.showMessageDialog(CardManagement.this, "数据删除成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
                    refreshJList(savedList); // 刷新列表以显示最新数据
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < textFields.length; i++) {
                    textFields[i].setText(""); // 清空文本框内容
                }
                refreshJList(savedList);
            }
        });

        pack(); // 调整窗口大小以适应组件
    }

    public DefaultListModel<String> getAllNamesAndIds() {
        DefaultListModel<String> nameIdListModel = new DefaultListModel<>();

        try {
            // 注册MySQL JDBC驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

            // 创建SQL查询，同时获取名字和ID
            PreparedStatement statement = connection.prepareStatement("SELECT id, name FROM person_info");

            // 执行查询
            ResultSet resultSet = statement.executeQuery();

            // 处理查询结果，获取名字和ID
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                nameIdListModel.addElement(id + ": " + name); // 将ID和名字结合为一个字符串
            }

            // 关闭连接
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        System.out.println(nameIdListModel);
        return nameIdListModel;
    }

    public String[] getPersonInfoById(String id) {
        String[] personInfo = new String[8]; // 因为有8列信息

        try {
            // 注册MySQL JDBC驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立数据库连接
            Connection connection = DriverManager.getConnection(this.url, this.username, this.password);

            // 创建SQL查询
            String sql = "SELECT * FROM person_info WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);

            // 执行查询
            ResultSet resultSet = statement.executeQuery();

            // 处理查询结果
            if (resultSet.next()) {
                personInfo[0] = resultSet.getString("id");
                personInfo[1] = resultSet.getString("name");
                personInfo[2] = resultSet.getString("gender");
                personInfo[3] = resultSet.getString("birthplace");
                personInfo[4] = resultSet.getString("phone");
                personInfo[5] = resultSet.getString("mobile");
                personInfo[6] = resultSet.getString("qq");
                personInfo[7] = resultSet.getString("wechat");
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

    public void refreshJList(JList<String> jList) {
        // 重新从数据库获取数据
        DefaultListModel<String> updatedModel = getAllNamesAndIds();

        // 更新JList的模型
        jList.setModel(updatedModel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CardManagement mainFrame = new CardManagement();
            mainFrame.setVisible(true);
        });
    }
}
