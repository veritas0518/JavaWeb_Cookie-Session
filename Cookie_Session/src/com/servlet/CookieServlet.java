package com.servlet;

import com.util.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: CookieServlet
 * @Description:
 * @Author: TianXing.Xue
 * @Date: 2021/9/7 15:51
 **/

public class CookieServlet extends BaseServlet {
    protected void testPath(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("path1", "path1");
        //req.getContextPath() ===> 得到工程路径
        cookie.setPath(req.getContextPath() + "/abc");
        resp.addCookie(cookie);
        resp.getWriter().write("创建了一个带有Path路径的Cookie");
    }

    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // 方案一：
//        // 1、先创建一个要修改的同名（指的就是 key）的 Cookie 对象
//        // 2、在构造器，同时赋于新的 Cookie 值。
//        Cookie cookie = new Cookie("key1", "newValue1");
//        // 3、调用 response.addCookie( Cookie );通知客户端 保存修改
//        resp.addCookie(cookie);

        // 方案二：
        // 1、先查找到需要修改的 Cookie 对象
        Cookie cookie = CookieUtils.findCookie("key1", req.getCookies());
        if (cookie != null) {
            // 2、调用 setValue()方法赋于新的 Cookie 值。
            cookie.setValue("newValue2");
            // 3、调用 response.addCookie()通知客户端保存修改
            resp.addCookie(cookie);
        }
        resp.getWriter().write("key1的cookie已经修改好");
    }

    protected void life3600(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("life3600", "life3600");
        cookie.setMaxAge(60 * 60); //设置cookie一小时后被删除，无效
        resp.addCookie(cookie);
        resp.getWriter().write("已经创建了一个存活一小时的cookie");
    }

    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先找到你要删除的Cookie对象
        Cookie cookie = CookieUtils.findCookie("key1", req.getCookies());
        if (cookie != null) {
            //调用setMaxAge(0);
            cookie.setMaxAge(0); //表示马上删除，都不需要等待浏览器关闭
            //调用resp.addCookie(cookie);
            resp.addCookie(cookie);

            resp.getWriter().write("key4的Cookie已经被删除");
        }
    }


    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("defaultLife", "defaultLife");
        cookie.setMaxAge(-1); //设置存活时间
        resp.addCookie(cookie);
    }

    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {
            //getName()方法返回Cookie的key(名)
            //getValue()方法返回Cookie的value值
            resp.getWriter().write("Cookie[" + cookie.getName() + "=" + cookie.getValue() + "]<br>");
        }

        Cookie iWantCookie = CookieUtils.findCookie("key1", cookies);


        //如果不等于null，说明赋过值，也就是找到了需要的Cookie
        if (iWantCookie != null) {
            resp.getWriter().write("找到了需要的Cookie");
        }
    }

    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.创建Cookie对象
        Cookie cookie = new Cookie("key1", "value1");
        //2.通知客户端保存Cookie
        //注意：通过服务器响应给客户端的都是resp
        resp.addCookie(cookie);

        resp.getWriter().write("Cookie创建成功");
    }
}
