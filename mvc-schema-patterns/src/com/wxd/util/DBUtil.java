package com.wxd.util;

import java.sql.*;

/**
 * author : 动力节点
 * 2019/3/14 0014
 */
public class DBUtil {

    private static String className="com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://localhost:3306/mvc";
    private static String username="root";
    private static String password="123456";
    private static  Connection conn=null;
    private static  PreparedStatement ps=null;
    //创建一个ThreadLocal对象
    private static ThreadLocal threadLocal = new ThreadLocal();
    static{
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn(){

        try {

            //首先到ThreadLocal索要当前线程关联的Connection
            conn = (Connection)threadLocal.get();
            if(conn==null){
                conn = DriverManager.getConnection(url, username,password);
                //将新建Connection与当前线程关联保存到ThreadLocal
                threadLocal.set(conn);
            }
            //conn.setAutoCommit(false);//设置事务为手动提交
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public static PreparedStatement  createStatement(String sql){
                     getConn();
                    try {
                        ps= conn.prepareStatement(sql);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    return ps;
    }

   public static void close(ResultSet rs){
       try {
           if(rs!=null){
               rs.close();
           }
           if(ps!=null){
               ps.close();
           }
           if(conn!=null){
               conn.close();
               threadLocal.remove();
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

   }

    public static void close( PreparedStatement ps,Connection conn){
       close(null, ps, conn);
    }

    public static void close(ResultSet rs, PreparedStatement ps,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
                threadLocal.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
