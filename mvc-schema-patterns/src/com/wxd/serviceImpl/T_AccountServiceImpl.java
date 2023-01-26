package com.wxd.serviceImpl;

import com.wxd.dao.T_AccountDao;
import com.wxd.daoImpl.T_AccountDaoImpl;
import com.wxd.service.T_AccountService;
import com.wxd.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * author : 动力节点
 * 2019/4/1 0001
 */
public class T_AccountServiceImpl implements T_AccountService {

    @Override
    public int transferAccount(String fromAccount, String toAccount, int balance) {

               int flag =1;//转账业务成功了
               int from_Balance=0,to_Balance=0;
               T_AccountDao dao  = new T_AccountDaoImpl();

               try{
                   dao.checkAccount(fromAccount);
                   dao.checkAccount(toAccount);
                   from_Balance=dao.getBalance(fromAccount);
                   to_Balance=dao.getBalance(toAccount);
                   dao.updateBalance(fromAccount, from_Balance-balance);
                   dao.updateBalance(toAccount, to_Balance+balance);
               }catch(Exception ex){
                   ex.printStackTrace();
                   flag = 0;
               }
        return flag;
    }
}
