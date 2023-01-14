package com.bjpowernode.bank.exception;

/**
 * @author wangxuedeng
 * @date 2022/9/26 - 16:45
 */
public class AppException extends Exception{
    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }
}
