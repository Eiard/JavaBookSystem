package BookDB.Frame;

import BookDB.DBwork.Book;
import BookDB.DBwork.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2021年10月22日19时38分
 */
public class BookFrame extends JFrame {
    /**
     * 对象封装集合
     */
    private List<Book> BookList = null;
    /**
     * jdbcTemplate对象
     */
    private JdbcTemplate template;

    /**
     * Swing
     */

    /**
     * 全局变量
    */
    Box TotalBox;

    /**
     * 第一层变量
     */
    JLabel IndexTag;
    Box IndexBox;

    /**
     * 第二层变量
     */
    JLabel BookNameLabel,FindCheck;
    JTextField BookNameText;
    JButton CheckButton,ResetButton,ShowAllButton;
    Box FindBox;

    /**
     * 第三层变量
     */
    Object bkStyle[]={"图书编号","书名","作者","出版社","价格","库存"};
    Object bkInfo[][] = {{"","","","","",""}};
    JScrollPane ShowMenu;
    JTable bkTable;
    Box TableBox;

    /**
     * 第四层变量
     */
    JLabel bkIDLabel,bkNameLabel,bkAuthorLabel;
    JTextField bkIDText,bkNameText,bkAuthorText;
    Box TestBoxOne;

    /**
     * 第五层变量
     */
    JLabel bkPressLabel,bkPriceLabel;
    JTextField bkPressText,bkPriceText;
    JButton InsertButton,ChangeButton,ResetButtonText;
    Box TestBoxTwo;

    /**
     * 第六层变量
     */
    JLabel DeleteIDLabel;
    JTextField DeleteIDText;
    JButton DeleteButton,ResetDeleteButton;
    Box DeleteBox;

    /**
     * 第七层变量
     */
    JLabel TagInfo;
    Box ShowBox;


    BookFrame(String TagName){
        super(TagName);
        setBounds(700,100,1100,650);

        /**
         * 初始化jdbcTemplate对象 (获取连接池)
         */
        template = new JdbcTemplate(JDBCUtils.getDataSource());
        /**

        /**
         * 全局盒子
         */
        TotalBox = Box.createVerticalBox();

        /**
         * 第一层盒子
         */
        IndexBox = Box.createHorizontalBox();
        IndexTag = new JLabel("图书管理");
        IndexTag.setForeground(Color.PINK);
        IndexTag.setFont(new Font("宋体", Font.PLAIN, 30));
        IndexBox.add(IndexTag);
        TotalBox.add(Box.createVerticalStrut(15));
        TotalBox.add(IndexBox);


        /**
         * 第二层盒子
         */
        FindBox = Box.createHorizontalBox();
        BookNameLabel = new JLabel("图书名称:");
        BookNameText = new JTextField(10);
        CheckButton = new JButton("查询");
        ResetButton = new JButton("重置");
        ShowAllButton = new JButton("查询所有");
        FindCheck = new JLabel();
        BookNameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        CheckButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ResetButton.setFont(new Font("宋体", Font.PLAIN, 12));
        FindCheck.setFont(new Font("宋体", Font.PLAIN, 15));
        ShowAllButton.setFont(new Font("宋体", Font.PLAIN, 12));
        CheckButton.addActionListener(e -> {
            if(e.getSource() == CheckButton){
                UpdateBookInfo();
                ToObject();
                String bkName = BookNameText.getText();
                int index = 0;
                /**
                 * 统计
                 */
                for(Book book: BookList){
                    if(book.getBkName().equals(bkName)){
                        index++;
                    }
                }
                if(index == 0){
                    FindCheck.setText("查询失败!");
                    FindCheck.setForeground(Color.red);
                    bkInfo = new Object[][]{{"", "", "", "", "", ""}};
                }
                else{
                    bkInfo = new Object[index][6];
                    int j = 0;
                    for(Book book: BookList){
                        if(book.getBkName().equals(bkName)) {
                            bkInfo[j][0] = book.getBkID();
                            bkInfo[j][1] = book.getBkName();
                            bkInfo[j][2] = book.getBkAuthor();
                            bkInfo[j][3] = book.getBkPress();
                            bkInfo[j][4] = book.getBkPrice();
                            if (book.getBkState() == 1) {
                                bkInfo[j][5] = "有";
                            } else {
                                bkInfo[j][5] = "无";
                            }
                            j++;
                        }
                    }
                    FindCheck.setText("查询成功!");
                    FindCheck.setForeground(Color.green);
                }
                bkTable = new JTable(bkInfo,bkStyle);
                bkTable.setEnabled(false);
                ShowMenu.setViewportView(bkTable);
            }
        });
        ResetButton.addActionListener(e -> {
            if(e.getSource() == ResetButton){
                BookNameText.setText("");
                FindCheck.setText("重置成功!");
                FindCheck.setForeground(Color.blue);
                bkInfo = new Object[][]{{"", "", "", "", "", ""}};
                bkTable = new JTable(bkInfo,bkStyle);
                bkTable.setEnabled(false);
                ShowMenu.setViewportView(bkTable);
            }
        });
        ShowAllButton.addActionListener(e -> {
            if(e.getSource() == ShowAllButton){
                UpdateBookInfo();
                ToObject();
                bkTable = new JTable(bkInfo,bkStyle);
                bkTable.setEnabled(false);
                ShowMenu.setViewportView(bkTable);
                FindCheck.setText("查询成功!");
                FindCheck.setForeground(Color.green);
            }
        });

        FindBox.add(BookNameLabel);
        FindBox.add(Box.createHorizontalStrut(10));
        FindBox.add(BookNameText);
        FindBox.add(Box.createHorizontalStrut(20));
        FindBox.add(CheckButton);
        FindBox.add(Box.createHorizontalStrut(20));
        FindBox.add(ResetButton);
        FindBox.add(Box.createHorizontalStrut(50));
        FindBox.add(FindCheck);
        FindBox.add(Box.createHorizontalStrut(150));
        FindBox.add(ShowAllButton);
        TotalBox.add(Box.createVerticalStrut(15));
        TotalBox.add(FindBox);


        /**
         * 第三层盒子
         */
        TableBox = Box.createHorizontalBox();
        bkTable = new JTable(bkInfo,bkStyle);
        bkTable.setEnabled(false);
        bkTable.setFont(new Font("宋体", Font.PLAIN, 12));
        bkTable.setBackground(Color.white);
        ShowMenu = new JScrollPane();
        ShowMenu.setViewportView(bkTable);
        ShowMenu.setPreferredSize(new Dimension(800, 250));
        TableBox.add(ShowMenu);
        TotalBox.add(Box.createVerticalStrut(15));
        TotalBox.add(TableBox);


        /**
         * 第四层盒子
         */
        TestBoxOne = Box.createHorizontalBox();
        bkIDLabel = new JLabel("图书编号:");
        bkNameLabel = new JLabel("书名:");
        bkAuthorLabel = new JLabel("作者:");
        bkIDText = new JTextField(20);
        bkNameText = new JTextField(20);
        bkAuthorText = new JTextField(20);
        bkIDLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        bkNameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        bkAuthorLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        TestBoxOne.add(bkIDLabel);
        TestBoxOne.add(Box.createHorizontalStrut(10));
        TestBoxOne.add(bkIDText);
        TestBoxOne.add(Box.createHorizontalStrut(30));
        TestBoxOne.add(bkNameLabel);
        TestBoxOne.add(Box.createHorizontalStrut(10));
        TestBoxOne.add(bkNameText);
        TestBoxOne.add(Box.createHorizontalStrut(30));
        TestBoxOne.add(bkAuthorLabel);
        TestBoxOne.add(Box.createHorizontalStrut(10));
        TestBoxOne.add(bkAuthorText);
        TotalBox.add(Box.createVerticalStrut(15));
        TotalBox.add(TestBoxOne);


        /**
         * 第五层盒子
         */

        TestBoxTwo = Box.createHorizontalBox();
        TestBoxTwo.setSize(new Dimension(100,100));
        bkPressLabel = new JLabel("出版社:");
        bkPriceLabel = new JLabel("价格:");
        bkPressText = new JTextField(20);
        bkPriceText = new JTextField(20);
        InsertButton = new JButton("添加");
        ChangeButton = new JButton("改");
        ResetButtonText = new JButton("重置");
        InsertButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ChangeButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ResetButtonText.setFont(new Font("宋体", Font.PLAIN, 12));
        bkPressLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        bkPriceLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        InsertButton.addActionListener(e -> {
            if (e.getSource() == InsertButton){
                UpdateBookInfo();
                int ExpFlag = 1;
                try{
                    Double.parseDouble(bkPriceText.getText());
                }catch (Exception exp){
                    ExpFlag = 0;
                }
                if(
                        ExpFlag == 1 &&
                        !bkIDText.getText().equals("") &&
                        !bkNameText.getText().equals("") &&
                        !bkAuthorText.getText().equals("") &&
                        !bkPressText.getText().equals("") &&
                        !bkPriceText.getText().equals("") ) {
                    int  InsertFlag= InsertBook(bkIDText.getText(), bkNameText.getText(), bkAuthorText.getText(), bkPressText.getText(), Double.parseDouble(bkPriceText.getText()));
                    if(InsertFlag == 1){
                        TagInfo.setText("插入成功!");
                        TagInfo.setForeground(Color.GREEN);
                    }
                    else if(InsertFlag == 0){
                        TagInfo.setText("插入失败!");
                        TagInfo.setForeground(Color.RED);
                    }else if(InsertFlag == -1){
                        TagInfo.setText("书籍编号重复!");
                        TagInfo.setForeground(Color.RED);
                    }
                }
                else{
                    if(ExpFlag == 0){
                        TagInfo.setText("价格请按格式输入!");
                    }
                    else {
                        TagInfo.setText("数据项不能为空!");
                    }
                    TagInfo.setForeground(Color.RED);
                }
            }
        });
        ChangeButton.addActionListener(e -> {
            if (e.getSource() == ChangeButton){
                UpdateBookInfo();
                int ExpFlag = 1;
                try{
                    Double.parseDouble(bkPriceText.getText());
                }catch (Exception exp){
                    ExpFlag = 0;
                }
                if(
                        ExpFlag == 1 &&
                                !bkIDText.getText().equals("") &&
                                !bkNameText.getText().equals("") &&
                                !bkAuthorText.getText().equals("") &&
                                !bkPressText.getText().equals("") &&
                                !bkPriceText.getText().equals("") ) {
                    int  InsertFlag= UpdateBook(bkIDText.getText(), bkNameText.getText(), bkAuthorText.getText(), bkPressText.getText(), Double.parseDouble(bkPriceText.getText()));
                    if(InsertFlag == 1){
                        TagInfo.setText("修改成功!");
                        TagInfo.setForeground(Color.GREEN);
                    }
                    else if(InsertFlag == 0){
                        TagInfo.setText("修改失败!");
                        TagInfo.setForeground(Color.RED);
                    }else if(InsertFlag == -1){
                        TagInfo.setText("书籍的编号不存在!");
                        TagInfo.setForeground(Color.RED);
                    }
                }
                else{
                    if(ExpFlag == 0){
                        TagInfo.setText("价格请按格式输入!");
                    }
                    else {
                        TagInfo.setText("数据项不能为空!");
                    }
                    TagInfo.setForeground(Color.RED);
                }
            }
        });
        ResetButtonText.addActionListener(e -> {
            if (e.getSource() == ResetButtonText){
                bkIDText.setText("");
                bkNameText.setText("");
                bkAuthorText.setText("");
                bkPressText.setText("");
                bkPriceText.setText("");
                TagInfo.setText("重置成功!!");
                TagInfo.setForeground(Color.GREEN);
            }
        });
        TestBoxTwo.add(Box.createHorizontalStrut(20));
        TestBoxTwo.add(bkPressLabel);
        TestBoxTwo.add(Box.createHorizontalStrut(10));
        TestBoxTwo.add(bkPressText);
        TestBoxTwo.add(Box.createHorizontalStrut(30));
        TestBoxTwo.add(bkPriceLabel);
        TestBoxTwo.add(Box.createHorizontalStrut(10));
        TestBoxTwo.add(bkPriceText);
        TestBoxTwo.add(Box.createHorizontalStrut(94));
        TestBoxTwo.add(InsertButton);
        TestBoxTwo.add(Box.createHorizontalStrut(30));
        TestBoxTwo.add(ChangeButton);
        TestBoxTwo.add(Box.createHorizontalStrut(30));
        TestBoxTwo.add(ResetButtonText);
        TotalBox.add(Box.createVerticalStrut(40));
        TotalBox.add(TestBoxTwo);


        /**
         * 第六层盒子
         */
        DeleteBox = Box.createHorizontalBox();
        DeleteIDLabel = new JLabel("图书编号:");
        DeleteIDText = new JTextField(20);
        DeleteButton = new JButton("删除");
        ResetDeleteButton = new JButton("重置");
        DeleteButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ResetDeleteButton.setFont(new Font("宋体", Font.PLAIN, 12));
        DeleteIDLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        DeleteButton.addActionListener(e -> {
            if(e.getSource() == DeleteButton){
                UpdateBookInfo();
                if(!DeleteIDText.getText().equals("")){
                    int DeleteFlag = DeleteBook(DeleteIDText.getText());
                    if(DeleteFlag == 1){
                        TagInfo.setText("删除成功!");
                        TagInfo.setForeground(Color.GREEN);
                    }
                    else if(DeleteFlag == 0){
                        TagInfo.setText("删除失败!");
                        TagInfo.setForeground(Color.RED);
                    }
                    else if(DeleteFlag == -1){
                        TagInfo.setText("书籍的编号不存在!");
                        TagInfo.setForeground(Color.RED);
                    }
                }else {
                    TagInfo.setText("请输入要删除的书的编号!");
                    TagInfo.setForeground(Color.RED);
                }
            }
        });
        ResetDeleteButton.addActionListener(e -> {
            if(e.getSource() == ResetDeleteButton){
                DeleteIDText.setText("");
                TagInfo.setText("重置删除项成功!!");
                TagInfo.setForeground(Color.GREEN);
            }
        });
        DeleteBox.add(DeleteIDLabel);
        DeleteBox.add(Box.createHorizontalStrut(10));
        DeleteBox.add(DeleteIDText);
        DeleteBox.add(Box.createHorizontalStrut(90));
        DeleteBox.add(DeleteButton);
        DeleteBox.add(Box.createHorizontalStrut(30));
        DeleteBox.add(ResetDeleteButton);
        DeleteBox.add(Box.createHorizontalStrut(395));
        TotalBox.add(Box.createVerticalStrut(40));
        TotalBox.add(DeleteBox);

        /**
         * 第七个盒子
         */
        ShowBox = Box.createHorizontalBox();
        TagInfo = new JLabel("欢迎使用图书管理!");
        TagInfo.setFont(new Font("宋体", Font.PLAIN, 30));
        ShowBox.add(TagInfo);
        TotalBox.add(Box.createVerticalStrut(30));
        TotalBox.add(ShowBox);


        this.add(TotalBox);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.validate();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void UpdateBookInfo(){
        String sql = "SELECT * FROM book";
        //template.query函数返回值是Book类型的List表   利用BeanPropertyRowMapper<类名>(类.class 字节码文件名)
        this.BookList = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
    }

    public void ToObject(){
        if(BookList != null) {
            bkInfo = new Object[BookList.size()][6];
            int i = 0;
            for (Book book :BookList) {
                bkInfo[i][0]=book.getBkID();
                bkInfo[i][1]=book.getBkName();
                bkInfo[i][2]=book.getBkAuthor();
                bkInfo[i][3]=book.getBkPress();
                bkInfo[i][4]=book.getBkPrice();
                if(book.getBkState()==1) {
                    bkInfo[i][5] = "有";
                }
                else {
                    bkInfo[i][5] = "无";
                }
                i++;
            }
        }
    }
    public int InsertBook(String bkID,String bkName,String bkAuthor,String bkPress,double bkPrice){
        int index = 0;
        int bkState = 1;
        for(Book book: BookList){
            if(book.getBkID().equals(bkID)){
                index++;
            }
        }
        if(index == 0){
            String sql = "INSERT INTO book(bkID,bkName,bkAuthor,bkPress,bkPrice,bkState) values(?,?,?,?,?,?)";
            int count = template.update(sql,bkID, bkName, bkAuthor, bkPress, bkPrice, bkState);
            if(count == 1) {
                return 1;
            }else{
                return 0;
            }
        }
        return -1;
    }

    public int UpdateBook(String bkID,String bkName,String bkAuthor,String bkPress,double bkPrice){
        int index = 0;
        int bkState = 1;
        for(Book book: BookList){
            if(book.getBkID().equals(bkID)){
                index++;
            }
        }
        if(index != 0){
            String sql = "UPDATE book SET bkName = ?,bkAuthor = ?,bkPress = ?,bkPrice = ? WHERE bkID = ?";
            int count = template.update(sql, bkName, bkAuthor, bkPress, bkPrice, bkID);
            if(count == 1) {
                return 1;
            }else{
                return 0;
            }
        }
        return -1;
    }


    public int DeleteBook(String bkID) {
        int index = 0;
        for (Book book : BookList) {
            if (book.getBkID().equals(bkID)) {
                index++;
            }
        }
        if (index != 0) {
            String sql = "Delete  FROM book WHERE bkID = ?";
            int count = template.update(sql, bkID);
            if (count == 1) {
                return 1;
            } else {
                return 0;
            }
        }
        return -1;
    }

}
