package com.example.demo.Servlet;

import com.example.demo.Bean.book;
import com.example.demo.Service.Service;
import com.example.demo.Service.ServiceImpl;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/hello-servlet")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Service si=new ServiceImpl();
//        List<book> books = si.selectAll();
//        for (book book : books) {
//            System.out.println(book);
//        }
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        System.out.println(name+":"+pwd);
        book b=new book("12345678","夏洛特的日记",1234);
        Gson gson=new Gson();
        String responseString= gson.toJson(b);
        resp.getWriter().write(responseString);
    }
}
