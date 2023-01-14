package com.bjpowernode.bank.exceptions;

/**
 * @author wangxuedeng
 * @date 2022/9/28 - 19:59
 */
public class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException() {
    }

    public MoneyNotEnoughException(String message) {
        super(message);
    }
}
