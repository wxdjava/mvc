package com.wxd.util;

import com.wxd.service.T_AccountService;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wangxuedeng
 * @date 2023/1/20 - 13:08
 */
public class Agent implements T_AccountService {

    T_AccountService service;

    public Agent(T_AccountService service) {
        this.service = service;
    }

    @Override
    public int transferAccount(String fromAccount, String toAccount, int balance) {
        Connection conn = DBUtil.getConn();

        try {
            conn.setAutoCommit(false);
            this.service.transferAccount(fromAccount,toAccount,balance);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DBUtil.close(null);
        }
        return 0;
    }
}
