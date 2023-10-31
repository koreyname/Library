package tyz.example.libraray.servlet.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

public class BaseServlet extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html;charset=UTF-8");
        String flag=req.getParameter("flag");
        Class c=this.getClass();
        Method declaredMethod = null;
        try {
            declaredMethod = c.getDeclaredMethod(flag, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
//            // 创建一个StringWriter对象
//            StringWriter sw = new StringWriter();
//
//            // 创建一个PrintWriter对象，并将它连接到StringWriter
//            PrintWriter pw = new PrintWriter(sw);
//
//            // 将异常堆栈信息打印到PrintWriter
//            e.printStackTrace(pw);
//
//            // 调用toString()方法获取异常堆栈信息字符串
//            String stackTraceStr = sw.toString();
//            req.setAttribute("error",stackTraceStr);
//            this.processTemplate("ErrorPage",req,resp);
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
}
