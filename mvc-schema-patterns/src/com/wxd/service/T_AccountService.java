package com.wxd.service;

/**
 * author : 动力节点
 * 2019/4/1 0001
 */
public interface T_AccountService {

    public int  transferAccount(String fromAccount,String toAccount,int balance);
}
