package com.bjpowernode.bank.exception;

/**
 * @author wangxuedeng
 * @date 2022/9/20 - 20:15
 */
public class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException() {
    }

    public MoneyNotEnoughException(String message) {
        super(message);
    }
}
