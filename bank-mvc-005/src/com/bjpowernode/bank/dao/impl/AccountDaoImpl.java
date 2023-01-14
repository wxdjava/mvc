package com.bjpowernode.bank.dao.impl;

import com.bjpowernode.bank.dao.AccountDao;
import com.bjpowernode.bank.pojo.Account;
import com.bjpowernode.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangxuedeng
 * @date 2022/10/5 - 19:15
 */
public class AccountDaoImpl implements AccountDao {
    public int insert(Account account){
        PreparedStatement ps = null;
        int count = 0;
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "insert into t_act(actno,balance) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,account.getActno());
            ps.setDouble(2,account.getBalance());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null,ps,null);
        }
        return count;
    }
    public int deleteById(long id){
        PreparedStatement ps = null;
        int count = 0;
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "delete from t_act where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null,ps,null);
        }
        return count;
    }
    public int update(Account account){
        PreparedStatement ps = null;
        int count = 0;
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "update t_act set balance = ? , actno = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1,account.getBalance());
            ps.setString(2,account.getActno());
            ps.setLong(3,account.getId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null,ps,null);
        }
        return count;
    }
    public Account selectActno(String actno){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Account account = null;
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "select id,balance from t_act where actno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,actno);
            rs = ps.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                Double balance = rs.getDouble("balance");
                //将结果封装成java对象
                account = new Account(id,actno,balance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null,ps,rs);
        }
        return account;
    }
    public List<Account> selectAll(){
        List<Account> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "select id,actno,balance from t_act";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String actno = rs.getString("actno");
                Double balance = rs.getDouble("balance");
                Account account = new Account(id,actno,balance);
                list.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null,ps,rs);
        }
        return list;
    }
}
