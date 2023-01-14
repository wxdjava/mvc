package com.bjpowernode.bank.service;

import com.bjpowernode.bank.dao.AccountDao;
import com.bjpowernode.bank.exceptions.AppException;
import com.bjpowernode.bank.exceptions.MoneyNotEnoughException;
import com.bjpowernode.bank.pojo.Account;
import com.bjpowernode.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author wangxuedeng
 * @date 2022/9/27 - 19:51
 */
public class AccountService {
    private AccountDao accountDao = new AccountDao();

    public void transfer(String fromActno,String toActno,double money) throws MoneyNotEnoughException, AppException {

        try (Connection connection = DBUtil.getConnection()){
            //开启事务
            connection.setAutoCommit(false);

            Account fromAct = accountDao.selectActno(fromActno);
            if (fromAct.getBalance() < money) {
                throw new MoneyNotEnoughException(("对不起，余额不足！"));
            }

            Account toAct = accountDao.selectActno(toActno);

            fromAct.setBalance(fromAct.getBalance() - money);
            toAct.setBalance(toAct.getBalance() + money);

            int count = accountDao.update(fromAct);

            /*String s = null;
            s.toString();*/

            count += accountDao.update(toAct);

            if (count != 2) {
                throw new AppException("账户转账异常！！！");
            }

            //提交事务
            connection.commit();
        } catch (SQLException e) {
            throw new AppException("账户转账异常！！！");
        }
    }
}
