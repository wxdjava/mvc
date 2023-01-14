package com.bjpowernode.bank.web;

import com.bjpowernode.bank.exceptions.MoneyNotEnoughException;
import com.bjpowernode.bank.service.AccountService;
import com.bjpowernode.bank.service.impl.AccountServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author wangxuedeng
 * @date 2022/9/26 - 19:33
 */
@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));

        AccountService accountService = new AccountServiceImpl();
        try {
            accountService.transfer(fromActno,toActno,money);
            response.sendRedirect(request.getContextPath() + "/success.jsp");
        } catch (MoneyNotEnoughException e) {
            response.sendRedirect(request.getContextPath() + "/moneynotenough.jsp");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
