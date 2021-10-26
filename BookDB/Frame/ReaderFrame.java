package BookDB.Frame;

import BookDB.DBwork.Borrow;
import BookDB.DBwork.JDBCUtils;
import BookDB.DBwork.Reader;
import BookDB.DBwork.ReaderType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2021年10月22日22时12分
 */
public class ReaderFrame extends JFrame {
    /**
     * 对象封装集合
     */
    private List<Reader> ReaderList = null;
    private List<ReaderType> ReaderTypeList = null;
    private List<Borrow> BorrowList = null;

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
    JLabel ReaderrdIDLabel,FindCheck;
    JTextField ReaderrdIDText;
    JButton CheckButton,ResetButton,ShowAllButton;
    Box FindBox;


    /**
     * 第三层变量
     */
    Object ReaderStyle[]={"读者编号","读者类别","读者姓名","学院","QQ","已借本数"};
    Object ReaderInfo[][] = {{"","","","","",""}};
    JScrollPane ShowMenu;
    JTable ReaderTable;
    Box TableBox;


    /**
     * 第四层变量
     */
    JLabel RdrdIDLabel,RdNameLabel;
    JComboBox<String> RdTypeIDComoBox;
    JTextField RdrdIDText, RdbkText,RdNameText;
    Box TestBoxOne;

    /**
     * 第五层变量
     */
    JLabel RdDeptLabel, RdQQLabel;
    JTextField RdDeptText, RdQQText;
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


    ReaderFrame(String TagName){
        super(TagName);
        setBounds(700,100,1100,650);
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
        IndexTag = new JLabel("读者管理");
        IndexTag.setForeground(Color.PINK);
        IndexTag.setFont(new Font("宋体", Font.PLAIN, 30));
        IndexBox.add(IndexTag);
        TotalBox.add(Box.createVerticalStrut(15));
        TotalBox.add(IndexBox);


        /**
         * 第二层盒子
         */
        FindBox = Box.createHorizontalBox();
        ReaderrdIDLabel = new JLabel("读者ID:");
        ReaderrdIDText = new JTextField(10);
        CheckButton = new JButton("查询");
        ResetButton = new JButton("重置");
        ShowAllButton = new JButton("查询所有");
        FindCheck = new JLabel();
        ReaderrdIDLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        CheckButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ResetButton.setFont(new Font("宋体", Font.PLAIN, 12));
        FindCheck.setFont(new Font("宋体", Font.PLAIN, 15));
        ShowAllButton.setFont(new Font("宋体", Font.PLAIN, 12));
        CheckButton.addActionListener(e -> {
            if(e.getSource() == CheckButton) {
                UpdateReaderInfo();
                ToObject();
                String ReaderID = ReaderrdIDText.getText();
                int index = 0;
                /**
                 * 统计
                 */
                for(Reader reader: ReaderList){
                    if(reader.getRdID().equals(ReaderID)){
                        index++;
                    }
                }
                if(index == 0){
                    FindCheck.setText("查询失败!");
                    FindCheck.setForeground(Color.red);
                    ReaderInfo = new Object[][]{{"", "", "", "","",""}};
                }
                else{
                    ReaderInfo = new Object[index][6];
                    int j = 0;
                    for(Reader reader: ReaderList){
                        if(reader.getRdID().equals(ReaderID)) {
                            ReaderInfo[j][0]=reader.getRdID();
                            UpdateReaderTypeInfo();
                            for(ReaderType readerType : ReaderTypeList) {
                                if(reader.getRdType() == readerType.getRdType()) {
                                    ReaderInfo[j][1] = readerType.getRdTypeName();
                                    break;
                                }
                            }
                            ReaderInfo[j][2]=reader.getRdName();
                            ReaderInfo[j][3]=reader.getRdDept();
                            ReaderInfo[j][4]=reader.getRdQQ();
                            ReaderInfo[j][5]=reader.getRdBorrowQty();
                            j++;
                        }
                    }
                    FindCheck.setText("查询成功!");
                    FindCheck.setForeground(Color.green);
                }
                ReaderTable = new JTable(ReaderInfo,ReaderStyle);
                ReaderTable.setEnabled(false);
                ShowMenu.setViewportView(ReaderTable);
            }
        });
        ResetButton.addActionListener(e -> {
            if(e.getSource() == ResetButton){
                FindCheck.setText("重置成功!");
                ReaderrdIDText.setText("");
                FindCheck.setForeground(Color.blue);
                ReaderInfo = new Object[][]{{"", "", "", "","",""}};
                ReaderTable = new JTable(ReaderInfo,ReaderStyle);
                ReaderTable.setEnabled(false);
                ShowMenu.setViewportView(ReaderTable);
            }
        });
        ShowAllButton.addActionListener(e -> {
            if(e.getSource() == ShowAllButton){
                UpdateReaderInfo();
                ToObject();
                ReaderTable = new JTable(ReaderInfo,ReaderStyle);
                ReaderTable.setEnabled(false);
                ShowMenu.setViewportView(ReaderTable);
                FindCheck.setText("查询成功!");
                FindCheck.setForeground(Color.green);
            }
        });

        FindBox.add(ReaderrdIDLabel);
        FindBox.add(Box.createHorizontalStrut(10));
        FindBox.add(ReaderrdIDText);
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
        ReaderTable = new JTable(ReaderInfo,ReaderStyle);
        ReaderTable.setEnabled(false);
        ReaderTable.setFont(new Font("宋体", Font.PLAIN, 12));
        ReaderTable.setBackground(Color.white);
        ShowMenu = new JScrollPane();
        ShowMenu.setViewportView(ReaderTable);
        ShowMenu.setPreferredSize(new Dimension(800, 250));
        TableBox.add(ShowMenu);
        TotalBox.add(Box.createVerticalStrut(15));
        TotalBox.add(TableBox);



        /**
         * 第四层盒子
         */
        /**
         * 下拉列表实现
         */
        UpdateReaderTypeInfo();
        TestBoxOne = Box.createHorizontalBox();
        RdTypeIDComoBox = new JComboBox<String>();
        for (ReaderType readerType:ReaderTypeList){
            RdTypeIDComoBox.addItem(readerType.getRdTypeName());
        }
        RdrdIDLabel = new JLabel("读者编号:");
        RdNameLabel = new JLabel("读者姓名");
        RdrdIDText = new JTextField(10);
        RdNameText = new JTextField(10);
        InsertButton = new JButton("添加");
        ChangeButton = new JButton("改");
        ResetButtonText = new JButton("重置");
        InsertButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ChangeButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ResetButtonText.setFont(new Font("宋体", Font.PLAIN, 12));
        RdrdIDLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        RdNameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        RdrdIDText.setFont(new Font("宋体", Font.PLAIN, 20));
        RdNameText.setFont(new Font("宋体", Font.PLAIN, 20));
        InsertButton.addActionListener(e -> {
            if (e.getSource() == InsertButton){
                UpdateReaderInfo();
                UpdateReaderTypeInfo();
                int Tag = 0;
                int RdType = 0;
                for (ReaderType readerType:ReaderTypeList){
                    if(Tag == RdTypeIDComoBox.getSelectedIndex()){
                        RdType = readerType.getRdType();
                    }
                    Tag++;
                }
                if(!RdrdIDText.getText().equals("")&& !RdNameText.getText().equals("")&& !RdDeptText.getText().equals("")&& !RdQQText.getText().equals("")){
                    int Flag = InsertReader(RdrdIDText.getText(),RdType,RdNameText.getText(),RdDeptText.getText(),RdQQText.getText());
                    if(Flag == 1){
                        TagInfo.setText("创建读者成功!");
                        TagInfo.setForeground(Color.GREEN);
                    }
                    else if(Flag == 0){
                        TagInfo.setText("读者创建失败!");
                        TagInfo.setForeground(Color.RED);
                    }
                    else if(Flag == -1){
                        TagInfo.setText("读者ID已存在!");
                        TagInfo.setForeground(Color.RED);
                    }
                }
                else{
                    TagInfo.setText("数据项不能为空!");
                    TagInfo.setForeground(Color.RED);
                }
            }
        });
        ChangeButton.addActionListener(e -> {
            if (e.getSource() == ChangeButton) {
                UpdateReaderInfo();
                UpdateReaderTypeInfo();
                int Tag = 0;
                int RdType = 0;
                for (ReaderType readerType : ReaderTypeList) {
                    if (Tag == RdTypeIDComoBox.getSelectedIndex()) {
                        RdType = readerType.getRdType();
                    }
                    Tag++;
                }

            if (!RdrdIDText.getText().equals("") && !RdNameText.getText().equals("") && !RdDeptText.getText().equals("") && !RdQQText.getText().equals("")) {
                int Flag = UpdateReader(RdrdIDText.getText(), RdType, RdNameText.getText(), RdDeptText.getText(), RdQQText.getText());
                if (Flag == 1) {
                    TagInfo.setText("修改读者信息成功!");
                    TagInfo.setForeground(Color.GREEN);
                } else if (Flag == 0) {
                    TagInfo.setText("修改读者信息失败!");
                    TagInfo.setForeground(Color.RED);
                } else if (Flag == -1) {
                    TagInfo.setText("读者ID不存在!");
                    TagInfo.setForeground(Color.RED);
                }
            } else {
                TagInfo.setText("数据项不能为空!");
                TagInfo.setForeground(Color.RED);
            }

            }
        });
        ResetButtonText.addActionListener(e -> {
            if (e.getSource() == ResetButtonText){
                RdrdIDText.setText("");
                RdNameText.setText("");
                RdDeptText.setText("");
                RdQQText.setText("");
                TagInfo.setText("重置成功!!");
                TagInfo.setForeground(Color.GREEN);
            }
        });

        TestBoxOne.add(RdrdIDLabel);
        TestBoxOne.add(Box.createHorizontalStrut(10));
        TestBoxOne.add(RdrdIDText);
        TestBoxOne.add(Box.createHorizontalStrut(80));
        TestBoxOne.add(RdTypeIDComoBox);
        TestBoxOne.add(Box.createHorizontalStrut(10));
        TestBoxOne.add(RdNameLabel);
        TestBoxOne.add(Box.createHorizontalStrut(10));
        TestBoxOne.add(RdNameText);
        TotalBox.add(Box.createVerticalStrut(15));
        TotalBox.add(TestBoxOne);


        /**
         * 第五层盒子
         */
        TestBoxTwo = Box.createHorizontalBox();
        TestBoxTwo.setSize(new Dimension(100,100));
        RdDeptLabel = new JLabel("学院:");
        RdQQLabel = new JLabel("QQ:");
        RdDeptText = new JTextField(10);
        RdQQText = new JTextField(10);
        RdDeptLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        RdQQLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        TestBoxTwo.add(Box.createHorizontalStrut(20));
        TestBoxTwo.add(RdDeptLabel);
        TestBoxTwo.add(Box.createHorizontalStrut(10));
        TestBoxTwo.add(RdDeptText);
        TestBoxTwo.add(Box.createHorizontalStrut(30));
        TestBoxTwo.add(RdQQLabel);
        TestBoxTwo.add(Box.createHorizontalStrut(10));
        TestBoxTwo.add(RdQQText);
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
        DeleteIDLabel = new JLabel("读者编号:");
        DeleteIDText = new JTextField(20);
        DeleteButton = new JButton("删除");
        ResetDeleteButton = new JButton("重置");
        DeleteButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ResetDeleteButton.setFont(new Font("宋体", Font.PLAIN, 12));
        DeleteIDLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        DeleteButton.addActionListener(e -> {
            if(e.getSource() == DeleteButton){
                UpdateReaderInfo();
                UpdateReaderTypeInfo();
                if(!DeleteIDText.getText().equals("")){
                    int DeleteFlag = DeleteReader(DeleteIDText.getText());
                    if(DeleteFlag == 1){
                        TagInfo.setText("删除成功!");
                        TagInfo.setForeground(Color.GREEN);
                    }
                    else if(DeleteFlag == 0){
                        TagInfo.setText("删除失败!");
                        TagInfo.setForeground(Color.RED);
                    }
                    else if(DeleteFlag == -1){
                        TagInfo.setText("读者ID不存在!");
                        TagInfo.setForeground(Color.RED);
                    }
                }else {
                    TagInfo.setText("请输入要删除的读者ID!");
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
        if(ReaderList != null) {
            ReaderInfo = new Object[ReaderList.size()][6];
            int i = 0;
            for (Reader reader :ReaderList) {
                ReaderInfo[i][0]=reader.getRdID();
                UpdateReaderTypeInfo();
                for(ReaderType readerType : ReaderTypeList) {
                    if(reader.getRdType() == readerType.getRdType()) {
                        ReaderInfo[i][1] = readerType.getRdTypeName();
                        break;
                    }
                }
                ReaderInfo[i][2]=reader.getRdName();
                ReaderInfo[i][3]=reader.getRdDept();
                ReaderInfo[i][4]=reader.getRdQQ();
                ReaderInfo[i][5]=reader.getRdBorrowQty();
                i++;
            }
        }
    }

    public int InsertReader(String rdID,int rdType,String rdName,String rdDept,String rdQQ){
        int indexID = 0;
        for (Reader reader : ReaderList) {
            if (reader.getRdID().equals(rdID)) {
                indexID++;
            }
        }
        if(indexID != 0){
            return -1;
        }
        String sql = "INSERT INTO reader(rdID, rdType, rdName, rdDept, rdQQ) values (?,?,?,?,?);";
        int count = template.update(sql, rdID,rdType,rdName,rdDept,rdQQ);
        if(count == 1){
            return 1;
        }
        else{
            return 0;
        }
    }

    public int UpdateReader(String rdID,int rdType,String rdName,String rdDept,String rdQQ){
        int indexID = 0;
        for (Reader reader : ReaderList) {
            if (reader.getRdID().equals(rdID)) {
                indexID++;
            }
        }
        if(indexID == 0){
            return -1;
        }
        String sql = "UPDATE reader SET rdType = ?,rdName = ?,rdDept = ?,rdQQ = ? WHERE rdID = ?;";
        int count = template.update(sql,rdType,rdName,rdDept,rdQQ,rdID);
        if(count == 1){
            return 1;
        }
        else{
            return 0;
        }
    }

    public int DeleteReader(String ReaderID){
        int index = 0;
        for (Reader reader : ReaderList) {
            if (reader.getRdID().equals(ReaderID)) {
                index++;
            }
        }
        if (index != 0) {
            String sql = "Delete  FROM reader WHERE rdID = ?";
            int count = template.update(sql, ReaderID);
            if (count == 1) {
                return 1;
            } else {
                return 0;
            }
        }
        return -1;
    }





}
