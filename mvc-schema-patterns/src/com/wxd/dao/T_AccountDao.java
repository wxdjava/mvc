package com.wxd.dao;

import java.sql.Connection;

/**
 * @author wangxuedeng
 * @date 2023/1/14 - 18:24
 */
public interface T_AccountDao {
    public int checkAccount(String account) throws Exception;

    public int getBalance(String account) throws Exception;

    public int updateBalance(String account,int balance) throws Exception;
}
