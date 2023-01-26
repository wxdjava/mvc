package com.wxd.test;

import com.wxd.service.T_AccountService;
import com.wxd.serviceImpl.T_AccountServiceImpl;
import com.wxd.util.Agent;

/**
 * @author wangxuedeng
 * @date 2023/1/14 - 18:42
 */
public class TestMain {
    public static void main(String[] args) {
        T_AccountService transterObj = new T_AccountServiceImpl();
        T_AccountService agent = new Agent(transterObj);
        agent.transferAccount("zhangsan","lisi",100);
    }
}
