package com.wxd.model;

/**
 * author : 动力节点
 * 2019/4/1 0001
 */
public class T_Account {
    private Integer id;
    private String  account;
    private Integer balance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public T_Account() {
    }

    public T_Account(Integer id, String account, Integer balance) {
        this.id = id;
        this.account = account;
        this.balance = balance;
    }
}
