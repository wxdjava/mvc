package com.wxd.daoImpl;

import com.wxd.dao.T_AccountDao;
import com.wxd.model.T_Account;
import com.wxd.util.DBUtil;
import com.wxd.util.SqlSession;

import java.sql.Connection;

/**
 * @author wangxuedeng
 * @date 2023/1/14 - 18:28
 */
public class T_AccountDaoImpl implements T_AccountDao {
    @Override
    public int checkAccount(String account) throws Exception {
        System.out.println("checkAcount:" + DBUtil.getConn());
        String sql = "select count(*) from t_account where account = '"+account+"'";
        int count = (Integer) SqlSession.selectOne(sql,Integer.class);
        return count;
    }

    @Override
    public int getBalance(String account) throws Exception {
        System.out.println("getBalance:" + DBUtil.getConn());
        String sql = "select balance from t_account where account = '"+account+"'";
        int balance = (Integer) SqlSession.selectOne(sql,Integer.class);
        return balance;
    }

    @Override
    public int updateBalance(String account, int balance) throws Exception {
        System.out.println("updateBalance:" + DBUtil.getConn());
        T_Account obj = new T_Account();
        obj.setAccount(account);
        obj.setBalance(balance);
        int flag = SqlSession.update(obj,"account");
        return flag;
    }
}
