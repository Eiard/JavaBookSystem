package BookDB.Frame;

import BookDB.DBwork.JDBCUtils;
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
public class ReaderTypeFrame extends JFrame {
    /**
     * 对象封装集合
     */
    private List<ReaderType> ReaderTypeList = null;
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
    JLabel ReaderTypeNameLabel,FindCheck;
    JTextField ReaderTypeNameText;
    JButton CheckButton,ResetButton,ShowAllButton;
    Box FindBox;

    /**
     * 第三层变量
     */
    Object ReaderTypeStyle[]={"读者类型ID","读者类型名","可借数量","可借天数"};
    Object ReaderTypeInfo[][] = {{"","","",""}};
    JScrollPane ShowMenu;
    JTable ReaderTypeTable;
    Box TableBox;

    /**
     * 第四层变量
     */
    JLabel ReaderTyperdTypeLabel,ReaderTyperdTypeNameLabel,ReaderTypecanlendQtyLabel,ReaderTypecanlendDayLabel;
    JTextField ReaderTyperdTypeText,ReaderTyperdTypeNameText,ReaderTypecanlendQtyText,ReaderTypecanlendDayText;
    Box TestBoxOne;

    /**
     * 第五层变量
     */
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

    ReaderTypeFrame(String TagName){
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
        IndexTag = new JLabel("读者类别管理");
        IndexTag.setForeground(Color.PINK);
        IndexTag.setFont(new Font("宋体", Font.PLAIN, 30));
        IndexBox.add(IndexTag);
        TotalBox.add(Box.createVerticalStrut(15));
        TotalBox.add(IndexBox);


        /**
         * 第二层盒子
         */
        FindBox = Box.createHorizontalBox();
        ReaderTypeNameLabel = new JLabel("读者类别名称:");
        ReaderTypeNameText = new JTextField(10);
        CheckButton = new JButton("查询");
        ResetButton = new JButton("重置");
        ShowAllButton = new JButton("查询所有");
        FindCheck = new JLabel();
        ReaderTypeNameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        CheckButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ResetButton.setFont(new Font("宋体", Font.PLAIN, 12));
        FindCheck.setFont(new Font("宋体", Font.PLAIN, 15));
        ShowAllButton.setFont(new Font("宋体", Font.PLAIN, 12));
        CheckButton.addActionListener(e -> {
            if(e.getSource() == CheckButton){
                UpdateReaderTypeInfo();
                ToObject();
                String ReaderTypeName = ReaderTypeNameText.getText();
                int index = 0;
                /**
                 * 统计
                 */
                for(ReaderType readerType: ReaderTypeList){
                    if(readerType.getRdTypeName().equals(ReaderTypeName)){
                        index++;
                    }
                }
                if(index == 0){
                    FindCheck.setText("查询失败!");
                    FindCheck.setForeground(Color.red);
                    ReaderTypeInfo = new Object[][]{{"", "", "", ""}};
                }
                else{
                    ReaderTypeInfo = new Object[index][4];
                    int j = 0;
                    for(ReaderType readerType: ReaderTypeList){
                        if(readerType.getRdTypeName().equals(ReaderTypeName)) {
                            ReaderTypeInfo[j][0] = readerType.getRdType();
                            ReaderTypeInfo[j][1] = readerType.getRdTypeName();
                            ReaderTypeInfo[j][2] = readerType.getCanlendQty();
                            ReaderTypeInfo[j][3] = readerType.getCanlendDay();
                            j++;
                        }
                    }
                    FindCheck.setText("查询成功!");
                    FindCheck.setForeground(Color.green);
                }
                ReaderTypeTable = new JTable(ReaderTypeInfo,ReaderTypeStyle);
                ReaderTypeTable.setEnabled(false);
                ShowMenu.setViewportView(ReaderTypeTable);
            }
        });
        ResetButton.addActionListener(e -> {
            if(e.getSource() == ResetButton){
                ReaderTypeNameText.setText("");
                FindCheck.setText("重置成功!");
                FindCheck.setForeground(Color.blue);
                ReaderTypeInfo = new Object[][]{{"", "", "", ""}};
                ReaderTypeTable = new JTable(ReaderTypeInfo,ReaderTypeStyle);
                ReaderTypeTable.setEnabled(false);
                ShowMenu.setViewportView(ReaderTypeTable);
            }
        });
        ShowAllButton.addActionListener(e -> {
            if(e.getSource() == ShowAllButton){
                UpdateReaderTypeInfo();
                ToObject();
                ReaderTypeTable = new JTable(ReaderTypeInfo,ReaderTypeStyle);
                ReaderTypeTable.setEnabled(false);
                ShowMenu.setViewportView(ReaderTypeTable);
                FindCheck.setText("查询成功!");
                FindCheck.setForeground(Color.green);
            }
        });

        FindBox.add(ReaderTypeNameLabel);
        FindBox.add(Box.createHorizontalStrut(10));
        FindBox.add(ReaderTypeNameText);
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
        ReaderTypeTable = new JTable(ReaderTypeInfo,ReaderTypeStyle);
        ReaderTypeTable.setEnabled(false);
        ReaderTypeTable.setFont(new Font("宋体", Font.PLAIN, 12));
        ReaderTypeTable.setBackground(Color.white);
        ShowMenu = new JScrollPane();
        ShowMenu.setViewportView(ReaderTypeTable);
        ShowMenu.setPreferredSize(new Dimension(800, 250));
        TableBox.add(ShowMenu);
        TotalBox.add(Box.createVerticalStrut(15));
        TotalBox.add(TableBox);


        /**
         * 第四层盒子
         */
        TestBoxOne = Box.createHorizontalBox();
        ReaderTyperdTypeLabel = new JLabel("读者类型编号:");
        ReaderTyperdTypeNameLabel = new JLabel("读者类型名:");
        ReaderTypecanlendQtyLabel = new JLabel("可借数量:");
        ReaderTypecanlendDayLabel = new JLabel("可借天数:");
        ReaderTyperdTypeText = new JTextField(10);
        ReaderTyperdTypeNameText = new JTextField(10);
        ReaderTypecanlendQtyText = new JTextField(10);
        ReaderTypecanlendDayText = new JTextField(10);
        ReaderTyperdTypeLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        ReaderTyperdTypeNameLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        ReaderTypecanlendQtyLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        ReaderTypecanlendDayLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        TestBoxOne.add(ReaderTyperdTypeLabel);
        TestBoxOne.add(Box.createHorizontalStrut(10));
        TestBoxOne.add(ReaderTyperdTypeText);
        TestBoxOne.add(Box.createHorizontalStrut(20));
        TestBoxOne.add(ReaderTyperdTypeNameLabel);
        TestBoxOne.add(Box.createHorizontalStrut(10));
        TestBoxOne.add(ReaderTyperdTypeNameText);
        TestBoxOne.add(Box.createHorizontalStrut(20));
        TestBoxOne.add(ReaderTypecanlendQtyLabel);
        TestBoxOne.add(Box.createHorizontalStrut(10));
        TestBoxOne.add(ReaderTypecanlendQtyText);
        TestBoxOne.add(Box.createHorizontalStrut(20));
        TestBoxOne.add(ReaderTypecanlendDayLabel);
        TestBoxOne.add(Box.createHorizontalStrut(10));
        TestBoxOne.add(ReaderTypecanlendDayText);
        TotalBox.add(Box.createVerticalStrut(15));
        TotalBox.add(TestBoxOne);


        /**
         * 第五层盒子
         */

        TestBoxTwo = Box.createHorizontalBox();
        TestBoxTwo.setSize(new Dimension(100,100));
        InsertButton = new JButton("添加");
        ChangeButton = new JButton("改");
        ResetButtonText = new JButton("重置");
        InsertButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ChangeButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ResetButtonText.setFont(new Font("宋体", Font.PLAIN, 12));
        InsertButton.addActionListener(e -> {
            if (e.getSource() == InsertButton){
                UpdateReaderTypeInfo();
                int TypeID = 0,TypeQty = 0,TypeDay = 0;
                int ExpFlag = 1;
                try{
                    TypeID = Integer.parseInt(ReaderTyperdTypeText.getText());
                    TypeQty = Integer.parseInt(ReaderTypecanlendQtyText.getText());
                    TypeDay = Integer.parseInt(ReaderTypecanlendDayText.getText());
                }catch (Exception exp){
                    /**
                     * 说明格式不对
                     */
                    ExpFlag = 0;
                }
                if(ExpFlag == 1 && !ReaderTyperdTypeNameText.getText().equals("")) {
                    int  InsertFlag= InsertReaderType(TypeID,ReaderTyperdTypeNameText.getText(),TypeQty, TypeDay);
                    if(InsertFlag == 1){
                        TagInfo.setText("插入成功!");
                        TagInfo.setForeground(Color.GREEN);
                    }
                    else if(InsertFlag == 0){
                        TagInfo.setText("插入失败!");
                        TagInfo.setForeground(Color.RED);
                    }else if(InsertFlag == -1){
                        TagInfo.setText("读者类型编号重复!");
                        TagInfo.setForeground(Color.RED);
                    }
                    else if(InsertFlag == -2){
                        TagInfo.setText("读者ID 可借数量 可借天数 需均大于0 有误!");
                        TagInfo.setForeground(Color.RED);
                    }
                }
                else{
                    if(ExpFlag == 0){
                        TagInfo.setText("可借天数-可借个数-读者类型ID请按整数格式输入!");
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
                    UpdateReaderTypeInfo();
                    int TypeID = 0, TypeQty = 0, TypeDay = 0;
                    int ExpFlag = 1;
                    try {
                        TypeID = Integer.parseInt(ReaderTyperdTypeText.getText());
                        TypeQty = Integer.parseInt(ReaderTypecanlendQtyText.getText());
                        TypeDay = Integer.parseInt(ReaderTypecanlendDayText.getText());
                    } catch (Exception exp) {
                        /**
                         * 说明格式不对
                         */
                        ExpFlag = 0;
                    }
                    if (ExpFlag == 1 && !ReaderTyperdTypeNameText.getText().equals("")) {
                        int InsertFlag = UpdateReaderType(TypeID, ReaderTyperdTypeNameText.getText(), TypeQty, TypeDay);
                        if (InsertFlag == 1) {
                            TagInfo.setText("修改成功!");
                            TagInfo.setForeground(Color.GREEN);
                        } else if (InsertFlag == 0) {
                            TagInfo.setText("修改失败!");
                            TagInfo.setForeground(Color.RED);
                        } else if (InsertFlag == -1) {
                            TagInfo.setText("读者类型名不存在!");
                            TagInfo.setForeground(Color.RED);
                        } else if (InsertFlag == -2) {
                            TagInfo.setText("读者ID 可借数量 可借天数 需均大于0 有误!");
                            TagInfo.setForeground(Color.RED);
                        }
                    } else {
                        if (ExpFlag == 0) {
                            TagInfo.setText("可借天数-可借个数-读者类型ID请按整数格式输入!");
                        } else {
                            TagInfo.setText("读者类型名不能为空!");
                        }
                        TagInfo.setForeground(Color.RED);
                    }
            }
        });
        ResetButtonText.addActionListener(e -> {
            if (e.getSource() == ResetButtonText){
                ReaderTyperdTypeText.setText("");
                ReaderTyperdTypeNameText.setText("");
                ReaderTypecanlendQtyText.setText("");
                ReaderTypecanlendDayText.setText("");
                TagInfo.setText("重置成功!!");
                TagInfo.setForeground(Color.GREEN);
            }
        });
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
        DeleteIDLabel = new JLabel("读者类型名:");
        DeleteIDText = new JTextField(20);
        DeleteButton = new JButton("删除");
        ResetDeleteButton = new JButton("重置");
        DeleteButton.setFont(new Font("宋体", Font.PLAIN, 12));
        ResetDeleteButton.setFont(new Font("宋体", Font.PLAIN, 12));
        DeleteIDLabel.setFont(new Font("宋体", Font.PLAIN, 20));
        DeleteButton.addActionListener(e -> {
            if(e.getSource() == DeleteButton){
                UpdateReaderTypeInfo();
                if(!DeleteIDText.getText().equals("")){
                    int DeleteFlag = DeleteReaderType(DeleteIDText.getText());
                    if(DeleteFlag == 1){
                        TagInfo.setText("删除成功!");
                        TagInfo.setForeground(Color.GREEN);
                    }
                    else if(DeleteFlag == 0){
                        TagInfo.setText("删除失败!");
                        TagInfo.setForeground(Color.RED);
                    }
                    else if(DeleteFlag == -1){
                        TagInfo.setText("读者类型名不存在!");
                        TagInfo.setForeground(Color.RED);
                    }
                }else {
                    TagInfo.setText("请输入要删除的读者类型名!");
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
        TagInfo = new JLabel("欢迎使用读者类型管理!");
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

    public void ToObject(){
        if(ReaderTypeList != null) {
            ReaderTypeInfo = new Object[ReaderTypeList.size()][4];
            int i = 0;
            for (ReaderType readerType :ReaderTypeList) {
                ReaderTypeInfo[i][0]=readerType.getRdType();
                ReaderTypeInfo[i][1]=readerType.getRdTypeName();
                ReaderTypeInfo[i][2]=readerType.getCanlendQty();
                ReaderTypeInfo[i][3]=readerType.getCanlendDay();
                i++;
            }
        }
    }
    public int InsertReaderType(int RdType,String RdName,int RdQty,int RdDay){
        if(RdType <= 0 || RdQty <= 0 || RdDay <= 0){
            return -2;
        }
        int index = 0;
        for(ReaderType readerType: ReaderTypeList){
            if(readerType.getRdType() == RdType){
                index++;
            }
        }
        if(index == 0){
            String sql = "INSERT INTO readertype(rdType,rdTypeName,canlendQty,canlendDay) values(?,?,?,?)";
            int count = template.update(sql,RdType, RdName, RdQty, RdDay);
            if(count == 1) {
                return 1;
            }else{
                return 0;
            }
        }
        return -1;
    }

    public int UpdateReaderType(int RdType,String RdName,int RdQty,int RdDay){
        if(RdType <= 0 || RdQty <= 0 || RdDay <= 0){
            return -2;
        }
        int index = 0;
        for(ReaderType readerType: ReaderTypeList){
            if(readerType.getRdType() == RdType){
                index++;
            }
        }
        if(index != 0){
            String sql = "UPDATE readertype SET rdTypeName = ?,canlendQty = ?,canlendDay = ? WHERE rdType = ?";
            int count = template.update(sql, RdName, RdQty, RdDay, RdType);
            if(count == 1) {
                return 1;
            }else{
                return 0;
            }
        }
        return -1;
    }

    public int DeleteReaderType(String readerTypeName) {
        int index = 0;
        for (ReaderType readerType : ReaderTypeList) {
            if (readerType.getRdTypeName().equals(readerTypeName)) {
                index++;
            }
        }
        if (index != 0) {
            String sql = "Delete  FROM readertype WHERE rdTypeName = ?";
            int count = template.update(sql, readerTypeName);
            if (count == 1) {
                return 1;
            } else {
                return 0;
            }
        }
        return -1;
    }

}
