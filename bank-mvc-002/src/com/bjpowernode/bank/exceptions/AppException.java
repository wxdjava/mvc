package com.bjpowernode.bank.exceptions;

/**
 * @author wangxuedeng
 * @date 2022/9/28 - 20:00
 */
public class AppException extends Exception{
    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }
}
