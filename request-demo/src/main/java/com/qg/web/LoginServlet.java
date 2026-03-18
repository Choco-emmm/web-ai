package com.qg.web;

import com.qg.dao.UserDao;
import com.qg.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserDao dao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        //2.调用Dao查询
        User user = dao.select(username, password);
        //3.获取字符输出流，并设置ContentType
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        //4.判断User是否为null
        if (user != null) {
            //登录成功
            writer.write("登录成功");
        } else {
            //登录失败
            writer.write("登录失败");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
