package com.bjpowernode.bank.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @author wangxuedeng
 * @date 2022/9/26 - 17:53
 */
public class DBUtil {
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources/jdbc");
    private static String driver = bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String user = bundle.getString("user");
    private static String password = bundle.getString("password");

    //构造方法私有化，工具类中的方法都是静态的，不需要创建对象
    private DBUtil(){

    }

    //类加载时注册驱动
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static ThreadLocal<Connection> local = new ThreadLocal<>();

    public static Connection getConnection() throws SQLException {
        Connection conn = local.get();
        if (conn == null) {
            conn = DriverManager.getConnection(url, user, password);
            local.set(conn);
        }
        return conn;
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
                local.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
