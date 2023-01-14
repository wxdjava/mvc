package com.bjpowernode.bank.dao;

import com.bjpowernode.bank.pojo.Account;

import java.util.List;

/**
 * @author wangxuedeng
 * @date 2022/9/28 - 21:53
 */
public interface AccountDao {
    int insert(Account account);
    int deleteById(long id);
    int update(Account account);
    Account selectActno(String actno);
    List<Account> selectAll();
}
