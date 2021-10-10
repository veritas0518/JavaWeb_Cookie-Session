package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: LoginServlet
 * @Description:
 * @Author: TianXing.Xue
 * @Date: 2021/9/8 13:34
 **/

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if("xtx0518".equalsIgnoreCase(username)&&"123456".equalsIgnoreCase(password)){
            //登入成功
            Cookie cookie = new Cookie("username",username);
            cookie.setMaxAge(60*60*24*7); //当前Cookie一周内有效
            resp.addCookie(cookie);
            System.out.println("登入成功");
        }else{
            //登入失败
            System.out.println("登入失败");
        }

    }
}
