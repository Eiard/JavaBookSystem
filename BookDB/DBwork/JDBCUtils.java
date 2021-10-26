package BookDB.DBwork;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2021年10月17日23时19分
 */
public class JDBCUtils {

    private static DataSource dataSource ;

    /**
     * 文件的读取，只需要读取一次即可拿到这些值。使用静态代码块
     * 从文件中读取信息
     * 然后进行Utils的属性初始化
     */
    //静态代码块初始化DataSource相关配置信息
    static{
        try {
            //1.加载配置文件
            Properties pro = new Properties();
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            //2.获取DataSource
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return 连接对象
     */
    public static Connection getConnection() throws SQLException {
        /**
         * 静态方法
         * 传入数据url user password
         * 返回Connection对象
         */
        return dataSource.getConnection();
    }

    public  void close(Statement stmt,Connection conn){
        close(null,stmt,conn);
    }

    public  void close(ResultSet rs , Statement stmt, Connection conn){


        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();//归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static DataSource getDataSource(){
        return  dataSource;
    }
}
