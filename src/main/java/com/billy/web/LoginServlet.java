package com.billy.web;

import com.billy.pojo.User;
import com.billy.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //获取复选框数据
        String remember = request.getParameter("remember");

        //调用service查询
        User user = service.login(username, password);

        //判断user
        if(user != null){
            //登陆成功，跳转到查询所有的brandServlet

            //判断是否勾选记住我
            if ("1".equals(remember)){
                //勾选了，发送cookie

                //创建cookie对象
                Cookie c_username = new Cookie("username",username);
                Cookie c_password = new Cookie("password",password);

                //设置cookie存活时间
                c_username.setMaxAge(60 * 60 * 24 * 7);
                c_password.setMaxAge(60 * 60 * 24 * 7);

                //发送

                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            //讲登录成功后的user对象，存储到session中
            HttpSession session = request.getSession();
            session.setAttribute("user",user);

            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath+"/hello.jsp");
        }else{
            //登陆失败

            //将错误信息存储到request中
            request.setAttribute("login_msg","用户名或密码错误");

            //跳转到login.jsp
            request.getRequestDispatcher("/login.jsp").forward(request,response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
