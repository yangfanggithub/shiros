package com.shiro.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "defaultServlet",urlPatterns = "/login")
public class DefaultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        // out info
        String contentType = request.getContentType();
        System.out.println(contentType);
        String url = request.getRequestURL().toString();
        System.out.println("url:");
        System.out.println(url);
        //认证
        UsernamePasswordToken token=new UsernamePasswordToken(name,pwd);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if(subject.isAuthenticated()){
                request.getRequestDispatcher("index.html").forward(request,response);
            }
        }catch (AuthenticationException az){
            az.printStackTrace();
        }
        response.sendRedirect("login.html");
    }


}
