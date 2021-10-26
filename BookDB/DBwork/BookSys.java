package BookDB.DBwork;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2021年10月17日22时05分
 */

/**
 * 分四种情况讨论
 * 1.读者类别管理
 * 2.图书管理
 * 3.读者管理
 * 4.借书还书管理
 * 5.退出
 */
public class BookSys {
    /**
     * 三个表的信息表
     */
    private List<Book> BookList = null;
    private List<Reader> ReaderList = null;
    private List<ReaderType> ReaderTypeList = null;
    private List<Borrow> BorrowList = null;
    /**
     * jdbcTemplate对象
     */
    private JdbcTemplate template;
    /**
     * 两个选择信号量
     */
    static int choice;
    static int Innerchoice;

    static Scanner scanner;
    public BookSys(){
        /**
         * 初始化jdbcTemplate对象 (获取连接池)
         */
        template = new JdbcTemplate(JDBCUtils.getDataSource());
        /**
         * 更新List表中数据
         */
        UpdateReaderInfo();
        UpdateBookInfo();
        UpdateReaderTypeInfo();
        UpdateBorrowInfo();


    }

    public  void Test() {
        BookSys BS = new BookSys();
        scanner = new Scanner(System.in);

        while(true){
            BS.Menu();
            choice = scanner.nextInt();

            /**
             * 读者类别管理
             * 1.添加读者类别
             * 2.修改读者类别
             * 3.删除读者类别
             * 4.显示所有读者类别
             * 5.退出
             */
            if(choice == 1){
                while(true) {
                    BS.ReaderTypeMenu();
                    Innerchoice = scanner.nextInt();
                    if(Innerchoice == 1){
                        BS.InsertReaderType();
                    }
                    else if(Innerchoice == 2){
                        BS.UpdateReaderType();
                    }
                    else if(Innerchoice == 3){
                        BS.DeleteReaderType();
                    }
                    else if(Innerchoice == 4){
                        if(BS.ReaderTypeList != null) {
                            for (ReaderType readerType : BS.ReaderTypeList) {
                                System.out.println(readerType);
                            }
                        }
                        else{
                            System.out.println("查询集为空");
                        }
                    }
                    else if(Innerchoice == 5){
                        break;
                    }
                    else {
                        System.out.println("输入有误,请重新输入!");
                    }
                    BS.UpdateReaderTypeInfo();
                }
            }
            /**
             * 图书管理
             * 1.添加图书
             * 2.修改图书
             * 3.查询图书
             * 4.删除图书
             * 5.显示所有图书信息
             * 6.退出
             */
            else if (choice == 2){
                while(true) {
                    BS.BookTypeMenu();
                    Innerchoice = scanner.nextInt();
                    if(Innerchoice == 1){
                        BS.InsertBook();
                    }
                    else if(Innerchoice == 2){
                        BS.UpdateBook();
                    }
                    else if(Innerchoice == 3){
                        if(BS.BookList != null) {
                            for (Book book : BS.BookList) {
                                System.out.println(book);
                            }
                        }
                        else{
                            System.out.println("查询集为空");
                        }
                    }
                    else if(Innerchoice == 4){
                        BS.DeleteBook();
                    }
                    else if(Innerchoice == 5){
                        if(BS.BookList != null) {
                            for (Book book : BS.BookList) {
                                System.out.println(book);
                            }
                        }
                        else{
                            System.out.println("查询集为空");
                        }
                    }
                    else if(Innerchoice == 6){
                        break;
                    }
                    else{
                        System.out.println("输入有误,请重新输入!");
                    }

                    /**
                     * 数据发生更改之后进行List更新
                     */
                    BS.UpdateBookInfo();
                }
            }
            /**
             * 读者管理
             * 1.添加读者
             * 2.修改读者
             * 3.删除读者
             * 4.显示所有读者信息
             * 5.退出
             */
            else if (choice == 3){
                while(true) {
                    BS.ReaderMenu();
                    Innerchoice = scanner.nextInt();
                    if(Innerchoice == 1){
                        BS.InsertReader();
                    }
                    else if(Innerchoice == 2){
                        BS.UpdateReader();
                    }
                    else if(Innerchoice == 3){
                        BS.DeleteReader();
                    }
                    else if(Innerchoice == 4){
                        if(BS.ReaderList != null) {
                            for (Reader reader : BS.ReaderList) {
                                System.out.println(reader);
                            }
                        }
                        else{
                            System.out.println("查询集为空");
                        }
                    }
                    else if(Innerchoice == 5){
                        break;
                    }
                    else{
                        System.out.println("输入有误,请重新输入!");
                    }
                    BS.UpdateReaderInfo();
                }
            }
            /**
             * 借书还书管理
             * 1.借书
             * 2.还书
             * 3.所有借书还书信息
             * 4.退出
             */
            else if (choice == 4){
                while(true) {
                    BS.BorrowMenu();
                    Innerchoice = scanner.nextInt();
                    if(Innerchoice == 1){
                        BS.LendBook();
                    }
                    else if(Innerchoice == 2){
                        BS.GiveBackBook();
                    }
                    else if(Innerchoice == 3){
                        if(BS.BorrowList != null) {
                            for (Borrow borrow : BS.BorrowList) {
                                System.out.println(borrow);
                            }
                        }
                        else{
                            System.out.println("查询集为空");
                        }
                    }
                    else if(Innerchoice == 4){
                        break;
                    }
                    else{
                        System.out.println("输入有误,请重新输入!");
                    }
                    BS.UpdateBorrowInfo();
                }
            }
            /**
             * 退出系统
             */
            else if(choice == 5){
                break;
            }
            else{
                System.out.println("输入有误,请重新输入!");
            }

        }
    }
    public void Menu(){
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("图书管理系统");
        System.out.println("1.读者类别管理");
        System.out.println("2.图书管理");
        System.out.println("3.读者管理");
        System.out.println("4.借书还书管理");
        System.out.println("5.退出");
        System.out.print("请输入序号-->");
    }

    public void ReaderTypeMenu(){
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("读书类别管理");
        System.out.println("1.添加读者类别");
        System.out.println("2.修改读者类别");
        System.out.println("3.删除读者类别");
        System.out.println("4.显示所有读者类别");
        System.out.println("5.退出");
        System.out.print("请输入序号-->");
    }

    public void BookTypeMenu(){
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("图书管理");
        System.out.println("1.添加图书");
        System.out.println("2.修改图书");
        System.out.println("3.查询图书");
        System.out.println("4.删除图书");
        System.out.println("5.显示所有图书信息");
        System.out.println("6.退出");
        System.out.print("请输入序号-->");
    }

    public void ReaderMenu(){
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("读者管理");
        System.out.println("1.添加读者");
        System.out.println("2.修改读者");
        System.out.println("3.删除读者");
        System.out.println("4.显示所有读者信息");
        System.out.println("5.退出");
        System.out.print("请输入序号-->");
    }

    public void BorrowMenu(){
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("借书还书管理");
        System.out.println("1.借书");
        System.out.println("2.还书");
        System.out.println("3.所有借书还书信息");
        System.out.println("4.退出");
        System.out.print("请输入序号-->");
    }


    /**
     * 更新List表中数据
     */
    public void UpdateReaderTypeInfo(){
        String sql = "SELECT * FROM readertype";
        //template.query函数返回值是Book类型的List表   利用BeanPropertyRowMapper<类名>(类.class 字节码文件名)
        this.ReaderTypeList = template.query(sql, new BeanPropertyRowMapper<ReaderType>(ReaderType.class));
    }

    public void UpdateBookInfo(){
        String sql = "SELECT * FROM book";
        //template.query函数返回值是Book类型的List表   利用BeanPropertyRowMapper<类名>(类.class 字节码文件名)
        this.BookList = template.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
    }

    public void UpdateReaderInfo(){
        String sql = "SELECT * FROM reader";
        //template.query函数返回值是Book类型的List表   利用BeanPropertyRowMapper<类名>(类.class 字节码文件名)
        this.ReaderList = template.query(sql, new BeanPropertyRowMapper<Reader>(Reader.class));
    }

    public void UpdateBorrowInfo(){
        String sql = "SELECT * FROM borrow";
        //template.query函数返回值是Book类型的List表   利用BeanPropertyRowMapper<类名>(类.class 字节码文件名)
        this.BorrowList = template.query(sql, new BeanPropertyRowMapper<Borrow>(Borrow.class));
    }


    /**
     * 向表中插入数据
     */
    public void InsertReaderType(){
        String sql = "INSERT INTO readertype(rdType,rdTypeName,canlendQty,canlendDay) values(?,?,?,?)";
        String rdTypeName = "";
        int rdType,canLendQty,canLendDay;
        int count = 0;
        for (ReaderType readerType : ReaderTypeList) {
            System.out.println(readerType);
        }
        System.out.print("请输入编号(请勿重复):");
        rdType = scanner.nextInt();
        System.out.print("请输入身份职位:");
        rdTypeName = scanner.next();
        System.out.print("请输入可借数量:");
        canLendQty = scanner.nextInt();
        System.out.print("请输入可借天数:");
        canLendDay = scanner.nextInt();
        try {
            count = template.update(sql, rdType, rdTypeName, canLendQty, canLendDay);
        }
        catch (Exception e){
            System.out.println("插入失败(ID重复)!");
        }
        if(count == 1){
            System.out.println("插入成功!");
        }
    }

    public void InsertBook(){
        String sql = "INSERT INTO book(bkID,bkName,bkAuthor,bkPress,bkPrice,bkState) values(?,?,?,?,?,?)";
        String bkID = "",bkName = "",bkAuthor = "",bkPress = "";
        double bkPrice = 0.00;
        int bkState = 1;
        int count = 0;
        System.out.print("请输入书号(格式bk+数字):");
        bkID = scanner.next();
        System.out.print("请输入书名:");
        bkName = scanner.next();
        System.out.print("请输入作者:");
        bkAuthor = scanner.next();
        System.out.print("请输入出版社:");
        bkPress = scanner.next();
        System.out.print("请输入书的价格:");
        bkPrice = scanner.nextDouble();
        try {
            count = template.update(sql, bkID, bkName, bkAuthor, bkPress, bkPrice, bkState);
        }
        catch (Exception e){
            System.out.println("插入失败(ID重复)!");
        }
        if(count == 1){
            System.out.println("插入成功!");
        }

    }

    public void InsertReader(){
        String sql = "INSERT INTO reader(rdID,rdType,rdName,rdDept,rdQQ) values(?,?,?,?,?)";
        String rdID = "",rdName = "",rdDept = "",rdQQ = "";
        int rdType;
        int count = 0;
        System.out.print("请输入读者ID(格式:rd+数字):");
        rdID = scanner.next();
        for (ReaderType readerType : ReaderTypeList) {
            System.out.println(readerType);
        }
        System.out.print("请输入读者类型:");
        rdType = scanner.nextInt();
        System.out.print("请输入读者姓名:");
        rdName = scanner.next();
        System.out.print("请输入读者单位:");
        rdDept = scanner.next();
        System.out.print("请输入读者QQ:");
        rdQQ = scanner.next();
        try {
            count = template.update(sql, rdID ,rdType, rdName, rdDept,rdQQ);
        }
        catch (Exception e){
            System.out.println("插入失败(ID重复)!");
        }
        if(count == 1){
            System.out.println("插入成功!");
        }
    }


    /**
     * 更改表中数据
     */
    public void UpdateReaderType(){
        System.out.print("请输入您需要更改的类型ID(数字):");
        int rdType;
        rdType = scanner.nextInt();
        String sql = "SELECT * FROM readertype WHERE rdType = ?";
        int count = 0;
        try {
            /**
             * template.queryForMap
             * 如果能顺利执行完成则说明能找到
             * 如果不能顺利执行完成就直接跳到catch中报错
             */
            template.queryForMap(sql, rdType);
            System.out.println("您输入的ID读者存在!");
            sql = "UPDATE readertype SET rdTypeName = ?,canlendQty = ?,canlendDay = ? WHERE rdType = ?";
            String rdTypeName = "";
            int canlendQty = 0,canlendDay = 0;
            System.out.print("请输入读者类型名:");
            rdTypeName = scanner.next();
            System.out.print("请输入可借数量:");
            canlendQty = scanner.nextInt();
            System.out.print("请输入可借天数:");
            canlendDay = scanner.nextInt();
            count = template.update(sql, rdTypeName, canlendQty, canlendDay, rdType);
            if (count == 1) {
                System.out.println("修改成功!");
            } else {
                System.out.println("修改失败!");
            }
        }
        catch (Exception e) {
            System.out.println("您输入的ID读者类型不存在!");
        }

    }

    public void UpdateBook(){
        System.out.print("请输入您需要更改的类型ID(bk+数字):");
        String bkID;
        bkID = scanner.next();
        String sql = "SELECT * FROM book WHERE bkID = ?";
        int count = 0;
        try {
            /**
             * template.queryForMap
             * 如果能顺利执行完成则说明能找到
             * 如果不能顺利执行完成就直接跳到catch中报错
             */
            template.queryForMap(sql, bkID);
            System.out.println("您输入的ID读者存在!");
            sql = "UPDATE book SET bkName = ?,bkAuthor = ?,bkPress = ?,bkPrice = ?,bkState = ? WHERE bkID = ?";
            String bkName = "",bkAuthor = "",bkPress = "";
            double bkPrice = 0.00;
            int bkState = 1;
            System.out.print("请输入书名:");
            bkName = scanner.next();
            System.out.print("请输入作者:");
            bkAuthor = scanner.next();
            System.out.print("请输入出版社:");
            bkPress = scanner.next();
            System.out.print("请输入书的价格:");
            bkPrice = scanner.nextDouble();
            count = template.update(sql, bkName, bkAuthor, bkPress, bkPrice, bkState, bkID);
            if (count == 1) {
                System.out.println("修改成功!");
            } else {
                System.out.println("修改失败!");
            }
        }
        catch (Exception e) {
            System.out.println("您输入的ID图书不存在!");
        }
    }

    public void UpdateReader(){
        System.out.print("请输入您需要更改的信息ID(格式:rd+数字):");
        String rdID;
        rdID = scanner.next();
        String sql = "SELECT * FROM reader WHERE rdID = ?";
        int count = 0;
        try {
            /**
             * template.queryForMap
             * 如果能顺利执行完成则说明能找到
             * 如果不能顺利执行完成就直接跳到catch中报错
             */
            template.queryForMap(sql, rdID);
            System.out.println("您输入的ID读者存在!");
            sql = "UPDATE reader SET rdType = ?,rdName = ?,rdDept = ?,rdQQ = ? WHERE rdID = ?";
            String rdName = "", rdDept = "", rdQQ = "";
            int rdType;
            for (ReaderType readerType : ReaderTypeList) {
                System.out.println(readerType);
            }
            System.out.print("请输入读者类型:");
            rdType = scanner.nextInt();
            System.out.print("请输入读者姓名:");
            rdName = scanner.next();
            System.out.print("请输入读者单位:");
            rdDept = scanner.next();
            System.out.print("请输入读者QQ:");
            rdQQ = scanner.next();
            count = template.update(sql, rdType, rdName, rdDept, rdQQ, rdID);
            if (count == 1) {
                System.out.println("修改成功!");
            } else {
                System.out.println("修改失败!");
            }
        }
        catch (Exception e) {
            System.out.println("您输入的ID读者不存在!");
        }
    }


    /**
     * 删除表中数据
     */
    public void DeleteReaderType(){
        System.out.print("请输入您需要删除的读者类型ID(数字):");
        int rdType;
        rdType = scanner.nextInt();
        String sql = "SELECT * FROM readertype WHERE rdType = ?";
        int count = 0;
        try {
            /**
             * template.queryForMap
             * 如果能顺利执行完成则说明能找到
             * 如果不能顺利执行完成就直接跳到catch中报错
             */
            template.queryForMap(sql, rdType);
            System.out.println("您输入的ID读者存在!");
            sql = "DELETE FROM readertype WHERE rdType = ?";
            count = template.update(sql,rdType);
            if (count == 1) {
                System.out.println("删除成功!");
            } else {
                System.out.println("删除失败!");
            }
        }
        catch (Exception e) {
            System.out.println("您输入的ID读者类型不存在!");
        }
    }

    public void DeleteBook(){
        System.out.print("请输入您需要删除的图书ID(bk+数字):");
        String bkID;
        bkID = scanner.next();
        String sql = "SELECT * FROM book WHERE bkID = ?";
        int count = 0;
        try {
            /**
             * template.queryForMap
             * 如果能顺利执行完成则说明能找到
             * 如果不能顺利执行完成就直接跳到catch中报错
             */
            template.queryForMap(sql, bkID);
            System.out.println("您输入的ID图书存在!");
            sql = "DELETE FROM book WHERE bkID = ?";
            count = template.update(sql,bkID);
            if (count == 1) {
                System.out.println("删除成功!");
            } else {
                System.out.println("删除失败!");
            }
        }
        catch (Exception e) {
            System.out.println("您输入的ID图书不存在!");
        }
    }

    public void DeleteReader(){
        System.out.print("请输入您需要删除的读者ID(rd+数字):");
        String rdID;
        rdID = scanner.next();
        String sql = "SELECT * FROM reader WHERE rdID = ?";
        int count = 0;
        try {
            /**
             * template.queryForMap
             * 如果能顺利执行完成则说明能找到
             * 如果不能顺利执行完成就直接跳到catch中报错
             */
            template.queryForMap(sql, rdID);
            System.out.println("您输入的ID读者存在!");
            sql = "DELETE FROM reader WHERE rdID = ?";
            count = template.update(sql,rdID);
            if (count == 1) {
                System.out.println("删除成功!");
            } else {
                System.out.println("删除失败!");
            }
        }
        catch (Exception e) {
            System.out.println("您输入的ID读者不存在!");
        }
    }


    /**
     * 借书还书
     */
    public void LendBook(){
        String rdID,bkID;
        Date DateBorrow = new Date(),DateGiveBack = null;
        String str=null;
        System.out.print("请输入读者ID:");
        rdID = scanner.next();
        System.out.println("所有书籍如下所示:");
        if(BookList != null) {
            for (Book book : BookList) {
                System.out.println(book);
            }
        }
        else{
            System.out.println("查询集为空");
        }
        System.out.print("请输入图书ID:");
        bkID = scanner.next();
        /**
         * 自动获取当前时间
         */
        SimpleDateFormat  SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("当前借书时间为"+SimpleDateFormat.format(DateBorrow.getTime()));
        System.out.print("请输入归还日期(格式yyyy-MM-dd):");
        str = scanner.next();
        try {
            DateGiveBack=new SimpleDateFormat("yyyy-MM-dd").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String sql = "INSERT INTO borrow values (?,?,?,?,null);";
        try {
            int count = template.update(sql, rdID, bkID, SimpleDateFormat.format(DateBorrow.getTime()), DateGiveBack);
            if(count == 1) {
            System.out.println("借书成功!");
            }
            else{
                System.out.println("借书失败");
            }
        }
        catch (Exception e){
            System.out.println("借书失败");
        }
    }

    public void GiveBackBook(){
        String rdID,bkID;
        System.out.print("请输入读者ID:");
        rdID = scanner.next();
        System.out.print("请输入图书ID:");
        bkID = scanner.next();
        String sql = "call in_GiveBack(?,?)";
        try {
            int count = template.update(sql, rdID, bkID);
            if(count == 1) {
                System.out.println("还书成功!");
            }
            else{
                System.out.println("还书失败");
            }
        }
        catch (Exception e){
            System.out.println("还书失败");
        }
    }
}
