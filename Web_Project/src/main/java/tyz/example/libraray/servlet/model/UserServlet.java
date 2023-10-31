package tyz.example.libraray.servlet.model;

import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;
import tyz.example.libraray.Dao.Impl.USerDao;
import tyz.example.libraray.Service.*;
import tyz.example.libraray.Bean.info;
import tyz.example.libraray.Service.iml.Userserviceiml;
import tyz.example.libraray.servlet.base.BaseServlet;
import tyz.example.libraray.servlet.util.CommonResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


public class UserServlet extends BaseServlet {
    //服务层
    Userservice userservice = new Userserviceiml();

    protected void toRegistPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processTemplate("user/regist", req, resp);
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
       //在注册的时候，还是要时事获取id的，不然会导致现场注册买书的时候user_id is null的情况实现脏数据
        String code = req.getParameter("rcode");
        //验证码
        Object right = req.getSession().getAttribute("KAPTCHA_SESSION_KEY");
        Boolean b;
        info in = new info();
        Map<String, String[]> map = req.getParameterMap();
        try {
            BeanUtils.populate(in, map);
        } catch (Exception e) {
            USerDao.disConnection();
            e.printStackTrace();
        }
        if (right.equals(code)) {
            //先获取人数
            int count=userservice.getNum();
            in.setId(count+1);
            b = userservice.regist(in);
        } else {
            b = false;
            //数据回显
            req.setAttribute("name", in.getName());
            req.setAttribute("pwd", in.getPwd());
            req.setAttribute("rpwd", in.getPwd());
            req.setAttribute("email", in.getEmail());
            req.setAttribute("code",code);
            req.setAttribute("errorMsg","验证码错误");
        }
        //响应转发
        if (b) {
            req.getSession().setAttribute("user", in);
            req.getSession().setMaxInactiveInterval(1000);
            try {
                processTemplate("user/regist_success", req, resp);
            } catch (IOException e) {
                USerDao.disConnection();
                throw new RuntimeException(e);
            }
        } else {
            try {
                this.toRegistPage(req,resp);
            } catch (ServletException e) {
                USerDao.disConnection();
                throw new RuntimeException(e);
            }
        }
    }

    protected void toLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processTemplate("user/login", req, resp);
    }

    protected void Login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //Servlet层
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        //Service层
        Userservice userservice = new Userserviceiml();
        info login = userservice.login(name, pwd);

        if (login == null) {
            //错误信息回显
            req.setAttribute("username", name);
            req.setAttribute("pwd", pwd);
            req.setAttribute("errMsg", "用户名或密码错误");
            processTemplate("user/login", req, resp);
        } else {
//            req.setAttribute("user_name", name);
//            ServletContext application = req.getServletContext();
//            application.setAttribute("user_name", name);
            req.getSession().setAttribute("user", login);
            req.getSession().setMaxInactiveInterval(1000);
            processTemplate("user/login_success", req, resp);
        }
    }

//    protected void Manager_Page(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        this.processTemplate("manager/book_manager", req, resp);
//    }

    protected void LogOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
    protected  void check_username(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String username=request.getParameter("username");
        //验证用户
        info in=userservice.check_user(username);
        CommonResult ok=null;
        if(in==null){
           ok=CommonResult.ok().setResultData("不错");
        }
        else ok=CommonResult.error();
        Gson gson=new Gson();
        String src=gson.toJson(ok);
        System.out.println(src);
        response.getWriter().write(src);
    }
}
