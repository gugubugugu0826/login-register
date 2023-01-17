package com.billy.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*
* 登陆验证过滤器
* */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;

        //判断资源路径是否和登录注册相关
        String[] urls = {"/login.jsp","/imgs","/css","/loginServlet","/register.jsp","registerServlet","checkCodeServlet"};
        //获取当前访问的资源路径
        String url = req.getRequestURL().toString();
        //循环判断
        for (String u : urls) {
            if(url.contains(u)){
                chain.doFilter(request, response);//放行

                return;
            }
        }

        //判断session中是否有user
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");

        //判断user是否为null
        if(user != null){
            chain.doFilter(request, response);
        }else{
            //跳转到登陆页面
            req.setAttribute("login_msg","您尚未登陆！");
            req.getRequestDispatcher("/login.jsp").forward(req,response);
        }
    }
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }


}
