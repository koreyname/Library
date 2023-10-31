package tyz.example.demo4;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
@WebServlet(value ="/Hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=gbk");
        PrintWriter printWriter=resp.getWriter();
        printWriter.println("你好！"+req.getParameter("name"));

    }
}