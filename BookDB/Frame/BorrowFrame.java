package BookDB.Frame;

import BookDB.DBwork.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2021年10月22日22时11分
 */
public class BorrowFrame extends JFrame {
    /**
     * 对象封装集合
     */
    private List<Book> BookList = null;
    private List<Reader> ReaderList = null;
    private List<Borrow> BorrowList = null;
    private List<ReaderType> ReaderTypeList = null;
    /**
     * jdbcTemplate对象
     */
    private JdbcTemplate template;
    private String OriginTime;

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
    JLabel BorrowrdIDLabel,FindCheck;
    JTextField BorrowrdIDText;
    JButton CheckButton,ResetButton,ShowAllButton;
    Box FindBox;

    /**
     * 第三层变量
     */
    Object BorrowStyle[]={"读者编号","图书编号","借书日期","应还日期","实际还书日期",};
    Object BorrowInfo[][] = {{"","","","",""}};
    JScrollPane ShowMenu;
    JTable BorrowTable;
    Box TableBox;

    /**
     * 第四层变量
     */
    JLabel brrdIDLabel,brbkIDLabel;
    JTextField brrdIDText,brbkText;
    Box TestBoxOne;

    /**
     * 第五层变量
     */
    JButton InsertButton,ResetButtonText;
    Box TestBoxTwo;

    /**
     * 第六层变量
     */
    JButton ReturnButton,ResetReturnButton;
    Box ReturnBox;

    /**
     * 第七层变量
     */
    JLabel TagInfo;
    Box ShowBox;






    BorrowFrame(String TagName){
        super(TagName);
        setBounds(700,100,1000,600);
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date OriginDate = new Date(0);
        OriginTime = SimpleDateFormat.format(OriginDate);
        /**
         * 初始化jdbcTemplate对象 (获取连接池)
         */
        template = new JdbcTemplate(JDBCUtils.getDataSource());

        /**
         * 全局盒子
         */
        TotalBox = Box.createVerticalBox();

        /**
         * 第一层盒子
         */
        IndexBox = Box.createHorizontalBox();
        IndexTag = new JLabel("借书还书管理");
        IndexTag.setForeground(Color.PINK);
        IndexTag.setFont(new Font("宋体", Font.PLAIN, 30));
        IndexBox.add(IndexTag);
        TotalBox.add(Box.createVerticalStrut(15));
        TotalBox.add(IndexBox);



        /**
         * 第二层盒子
         */
        
        FindBox = Box.createHorizontalBox();
        BorrowrdIDLabel = new JLabel("读者ID:");
        BorrowrdIDText = new JTextField(10);
        CheckButton = new JButton("查询");
        ResetButton = new JButton("重置");
        ShowAllButton = new JButton("查询所有");
        FindCheck = new JLabel();
        BorrowrdIDLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        CheckButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ResetButton.setFont(new Font("宋体", Font.PLAIN, 12));
        FindCheck.setFont(new Font("宋体", Font.PLAIN, 15));
        ShowAllButton.setFont(new Font("宋体", Font.PLAIN, 12));
        CheckButton.addActionListener(e -> {
            if(e.getSource() == CheckButton){
                UpdateBorrowInfo();
                ToObject();
                String borrowRd = BorrowrdIDText.getText();
                int index = 0;
                /**
                 * 统计
                 */
                for(Borrow borrow: BorrowList){
                    if(borrow.getRdID().equals(borrowRd)){
                        index++;
                    }
                }
                if(index == 0){
                    FindCheck.setText("查询失败!");
                    FindCheck.setForeground(Color.red);
                    BorrowInfo = new Object[][]{{"", "", "", "", ""}};
                }
                else{
                    BorrowInfo = new Object[index][5];
                    int j = 0;
                    for(Borrow borrow: BorrowList){
                        if(borrow.getRdID().equals(borrowRd)) {
                            BorrowInfo[j][0]=borrow.getRdID();
                            BorrowInfo[j][1]=borrow.getBkID();
                            BorrowInfo[j][2]=borrow.getDateBorrow();
                            BorrowInfo[j][3]=borrow.getDateLendPlan();
                            if(borrow.getDateLendAct().equals(null)){
                                BorrowInfo[j][4]="尚未归还";
                            }else {
                                BorrowInfo[j][4] = borrow.getDateLendAct();
                            }
                            j++;
                        }
                    }
                    FindCheck.setText("查询成功!");
                    FindCheck.setForeground(Color.green);
                }
                BorrowTable = new JTable(BorrowInfo,BorrowStyle);
                BorrowTable.setEnabled(false);
                ShowMenu.setViewportView(BorrowTable);
            }
        });
        ResetButton.addActionListener(e -> {
            if(e.getSource() == ResetButton){
                BorrowrdIDText.setText("");
                FindCheck.setText("重置成功!");
                FindCheck.setForeground(Color.blue);
                BorrowInfo = new Object[][]{{"", "", "", "", ""}};
                BorrowTable = new JTable(BorrowInfo,BorrowStyle);
                BorrowTable.setEnabled(false);
                ShowMenu.setViewportView(BorrowTable);
            }
        });
        ShowAllButton.addActionListener(e -> {
            if(e.getSource() == ShowAllButton){
                UpdateBorrowInfo();
                ToObject();
                BorrowTable = new JTable(BorrowInfo,BorrowStyle);
                BorrowTable.setEnabled(false);
                ShowMenu.setViewportView(BorrowTable);
                FindCheck.setText("查询成功!");
                FindCheck.setForeground(Color.green);
            }
        });

        FindBox.add(BorrowrdIDLabel);
        FindBox.add(Box.createHorizontalStrut(10));
        FindBox.add(BorrowrdIDText);
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
        BorrowTable = new JTable(BorrowInfo,BorrowStyle);
        BorrowTable.setEnabled(false);
        BorrowTable.setFont(new Font("宋体", Font.PLAIN, 12));
        BorrowTable.setBackground(Color.white);
        ShowMenu = new JScrollPane();
        ShowMenu.setViewportView(BorrowTable);
        ShowMenu.setPreferredSize(new Dimension(800, 250));
        TableBox.add(ShowMenu);
        TotalBox.add(Box.createVerticalStrut(15));
        TotalBox.add(TableBox);


        /**
         * 第四层盒子
         */


        TestBoxOne = Box.createHorizontalBox();
        brrdIDLabel = new JLabel("读者编号:");
        brbkIDLabel = new JLabel("图书编号");
        brbkText = new JTextField(20);
        brrdIDText = new JTextField(20);
        brrdIDLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        brbkIDLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        brbkText.setFont(new Font("宋体", Font.PLAIN, 20));
        brrdIDText.setFont(new Font("宋体", Font.PLAIN, 20));
        TestBoxOne.add(brrdIDLabel);
        TestBoxOne.add(Box.createHorizontalStrut(10));
        TestBoxOne.add(brrdIDText);
        TestBoxOne.add(Box.createHorizontalStrut(80));
        TestBoxOne.add(brbkIDLabel);
        TestBoxOne.add(Box.createHorizontalStrut(10));
        TestBoxOne.add(brbkText);
        TotalBox.add(Box.createVerticalStrut(15));
        TotalBox.add(TestBoxOne);

        /**
         * 第五层盒子
         */
        TestBoxTwo = Box.createHorizontalBox();
        TestBoxTwo.setSize(new Dimension(100,100));
        InsertButton = new JButton("借书");
        ReturnButton = new JButton("还书");
        ResetButtonText = new JButton("重置");
        InsertButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ReturnButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ResetButtonText.setFont(new Font("宋体", Font.PLAIN, 12));
        InsertButton.addActionListener(e -> {
            if (e.getSource() == InsertButton){
                UpdateBorrowInfo();
                UpdateBookInfo();
                UpdateReaderInfo();
                UpdateReaderTypeInfo();
                if(!brrdIDText.getText().equals("") && !brbkText.getText().equals("")) {
                    int  InsertFlag= InsertBorrow(brrdIDText.getText(), brbkText.getText());
                    if(InsertFlag == 1){
                        TagInfo.setText("借书成功!");
                        TagInfo.setForeground(Color.GREEN);
                    }
                    else if(InsertFlag == 0){
                        TagInfo.setText("借书失败!");
                        TagInfo.setForeground(Color.RED);
                    }
                    else if(InsertFlag == -2){
                        TagInfo.setText("读者不存在!");
                        TagInfo.setForeground(Color.RED);
                    }
                    else if(InsertFlag == -3){
                        TagInfo.setText("书籍不存在!");
                        TagInfo.setForeground(Color.RED);
                    }
                    else if(InsertFlag == -4){
                        TagInfo.setText("请先还书!");
                        TagInfo.setForeground(Color.RED);
                    }
                }
                else if(brrdIDText.getText().equals("")&&brbkText.getText().equals("")){
                    TagInfo.setText("请输入借书读者和书的编号!");
                    TagInfo.setForeground(Color.RED);
                }
                else if(brrdIDText.getText().equals("")){
                    TagInfo.setText("请输入借书读者的编号!");
                    TagInfo.setForeground(Color.RED);
                }
                else if(brbkText.getText().equals("")){
                    TagInfo.setText("请输入图书编号!");
                    TagInfo.setForeground(Color.RED);
                }
            }
        });

        ReturnButton.addActionListener(e -> {
            if(e.getSource() == ReturnButton){
                UpdateBorrowInfo();
                if(!brrdIDText.getText().equals("")&&!brbkText.getText().equals("")){
                    int ReturnFlag = ReturnBook(brrdIDText.getText(),brbkText.getText());
                    if(ReturnFlag == 1){
                        TagInfo.setText("还书成功!");
                        TagInfo.setForeground(Color.GREEN);
                    }
                    else if(ReturnFlag == 0){
                        TagInfo.setText("还书失败!");
                        TagInfo.setForeground(Color.RED);
                    }
                    else if(ReturnFlag == -1){
                        TagInfo.setText("借书记录不存在!");
                        TagInfo.setForeground(Color.RED);
                    }
                }else if(brrdIDText.getText().equals("")&&brbkText.getText().equals("")){
                    TagInfo.setText("请输入还书读者和书的编号!");
                    TagInfo.setForeground(Color.RED);
                }
                else if(brrdIDText.getText().equals("")){
                    TagInfo.setText("请输入还书读者的编号!");
                    TagInfo.setForeground(Color.RED);
                }
                else if(brbkText.getText().equals("")){
                    TagInfo.setText("请输入图书编号!");
                    TagInfo.setForeground(Color.RED);
                }
            }
        });

        ResetButtonText.addActionListener(e -> {
            if (e.getSource() == ResetButtonText){
                brrdIDText.setText("");
                brbkText.setText("");
                TagInfo.setText("重置成功!!");
                TagInfo.setForeground(Color.GREEN);
            }
        });

        TestBoxTwo.add(Box.createHorizontalStrut(20));
        TestBoxTwo.add(InsertButton);
        TestBoxTwo.add(Box.createHorizontalStrut(30));
        TestBoxTwo.add(ReturnButton);
        TestBoxTwo.add(Box.createHorizontalStrut(30));
        TestBoxTwo.add(ResetButtonText);
        TotalBox.add(Box.createVerticalStrut(40));
        TotalBox.add(TestBoxTwo);


        /**
         * 第六层盒子
         */

        ShowBox = Box.createHorizontalBox();
        TagInfo = new JLabel("欢迎使用借书还书管理!");
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
    public void UpdateBorrowInfo(){
        String sql = "SELECT * FROM borrow";
        //template.query函数返回值是Book类型的List表   利用BeanPropertyRowMapper<类名>(类.class 字节码文件名)
        this.BorrowList = template.query(sql, new BeanPropertyRowMapper<Borrow>(Borrow.class));
    }
    public void UpdateBookInfo(){
        String sql = "SELECT * FROM book";
        //template.query函数返回值是Book类型的List表   利用BeanPropertyRowMapper<类名>(类.class 字节码文件名)
        this.BookList = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
    }
    public void UpdateReaderTypeInfo(){
        String sql = "SELECT * FROM readertype";
        //template.query函数返回值是Book类型的List表   利用BeanPropertyRowMapper<类名>(类.class 字节码文件名)
        this.ReaderTypeList = template.query(sql, new BeanPropertyRowMapper<ReaderType>(ReaderType.class));
    }
    public void UpdateReaderInfo(){
        String sql = "SELECT * FROM reader";
        //template.query函数返回值是Book类型的List表   利用BeanPropertyRowMapper<类名>(类.class 字节码文件名)
        this.ReaderList = template.query(sql, new BeanPropertyRowMapper<Reader>(Reader.class));
    }

    public void ToObject(){
        if(BorrowList != null) {
            BorrowInfo = new Object[BorrowList.size()][5];
            int i = 0;
            for (Borrow borrow :BorrowList) {
                BorrowInfo[i][0]=borrow.getRdID();
                BorrowInfo[i][1]=borrow.getBkID();
                BorrowInfo[i][2]=borrow.getDateBorrow();
                BorrowInfo[i][3]=borrow.getDateLendPlan();
                if(borrow.getDateLendAct().equals(OriginTime)){
                    BorrowInfo[i][4]="尚未归还";
                }else {
                    BorrowInfo[i][4] = borrow.getDateLendAct();
                }
                i++;
            }
        }
    }

    public int InsertBorrow(String rdID,String bkId) {
        int index = 0;
        int indexRd = 0;
        int indexBk = 0;
        int count = 0;
        int innercount = 0;
        int indexReaderType = 0;
        int readerTypeNum = 0;
        /**
         * 根据读者类别确认可借书日期
         */
        for (Reader reader : ReaderList) {
            if (reader.getRdID().equals(rdID)) {
                indexReaderType = reader.getRdType();
                indexRd++;
            }
        }

        for (ReaderType readerType : ReaderTypeList) {
            if (readerType.getRdType() == indexReaderType) {
                readerTypeNum = readerType.getCanlendDay();
            }
        }
        if (indexRd == 0) {
            return -2;
        }
        for (Book book : BookList) {
            if (book.getBkID().equals(bkId)) {
                indexBk++;
            }
        }
        if (indexBk == 0) {
            return -3;
        }
        String TagTime = "";
        for (Borrow borrow : BorrowList) {
            if (borrow.getRdID().equals(rdID) && borrow.getBkID().equals(bkId)) {
                index++;
                TagTime = borrow.getDateLendAct();
            }
        }
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String DataBorrow = SimpleDateFormat.format(Calendar.getInstance().getTime());
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + readerTypeNum);
        String DataBorrowPlan = SimpleDateFormat.format(new Date(calendar.getTimeInMillis()));
        String sql;
        if (index == 0) {
            /**
             * 表中无此数据
             */
            sql = "INSERT INTO borrow values (?,?,?,?,?);";

        }
        else {
            /**
             * 曾经借过这本书
             */
            if(TagTime.equals(OriginTime)){
                return -4;
            }
            String sqlDelete = "DELETE FROM borrow  WHERE bkID = ? && rdID = ?";
            template.update(sqlDelete,bkId,rdID);
            sql = "INSERT INTO borrow values (?,?,?,?,?);";
        }
        String sqlReader = "UPDATE reader set rdBorrowQty = rdBorrowQty + 1 WHERE rdID = ? ";
        count = template.update(sql, rdID, bkId, DataBorrow, DataBorrowPlan,OriginTime);
        innercount = template.update(sqlReader,rdID);
        if (count == 1 && innercount == 1) {
            return 1;
        } else {
            return 0;
        }
    }
    public int ReturnBook(String rdID,String bkId){
        int index = 0;
        for(Borrow borrow:BorrowList){
            if(borrow.getRdID().equals(rdID)&&borrow.getBkID().equals(bkId)){
                index++;
            }
        }
        if(index == 0){
            return -1;
        }
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String DataBorrow = SimpleDateFormat.format(Calendar.getInstance().getTime());
        String sql = "UPDATE borrow SET DateLendAct = ? WHERE bkID = ? && rdID = ?";
        int count = template.update(sql, DataBorrow,bkId,rdID);
        int innercount = 0;
        String sqlReader = "UPDATE reader set rdBorrowQty = rdBorrowQty - 1 WHERE rdID = ? ";
        innercount = template.update(sqlReader,rdID);
        if(count == 1 && innercount == 1) {
            return 1;
        }else{
            return 0;
        }
    }
}
