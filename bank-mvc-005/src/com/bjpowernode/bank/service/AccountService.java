package com.bjpowernode.bank.service;

import com.bjpowernode.bank.exceptions.AppException;
import com.bjpowernode.bank.exceptions.MoneyNotEnoughException;

/**
 * @author wangxuedeng
 * @date 2022/9/28 - 21:56
 */
public interface AccountService {
    void transfer(String fromActno,String toActno,double money) throws MoneyNotEnoughException, AppException;
}
