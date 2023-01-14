package com.bjpowernode.bank.web.servlet;

import com.bjpowernode.bank.exception.AppException;
import com.bjpowernode.bank.exception.MoneyNotEnoughException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author wangxuedeng
 * @date 2022/9/2 - 19:06
 */
@WebServlet("/transfer")
public class AccountTransferServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取转账信息
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));

        //获取响应流对象
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //编写转账业务逻辑代码，连接数据库，进行转账操作
        //转账前判断余额是否充足

        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        int count = 0;
        try {
            //1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获取连接
            String url = "jdbc:mysql://localhost:3306/mvc";
            String user = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url,user,password);

            //开启事务（默认执行一条SQL语句自动提交）
            conn.setAutoCommit(false);

            //3.获取数据库操作对象
            String sql = "select balance from t_act where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,fromActno);
            //4.执行sql,返回结果集
            rs = ps.executeQuery();
            //5.处理结果集
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if(balance < money){
                    throw new MoneyNotEnoughException("对不起，余额不足！");
                }

                //程序执行到这里，说明余额充足
                String sql1 = "update t_act set balance = balance - ? where actno = ?";
                ps1 = conn.prepareStatement(sql1);
                ps1.setDouble(1,money);
                ps1.setString(2,fromActno);
                count = ps1.executeUpdate();

                //模拟异常
                /*String s = null;
                s.toString();*/

                String sql2 = "update t_act set balance = balance + ? where actno = ?";
                ps2 = conn.prepareStatement(sql2);
                ps2.setDouble(1,money);
                ps2.setString(2,toActno);
                count += ps2.executeUpdate();

                if (count != 2) {
                    throw new AppException("转账失败！请联系管理员");
                }

                //手动提交事务
                conn.commit();

                out.print("转账成功！");
            }
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            //e.printStackTrace();
            out.print(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
