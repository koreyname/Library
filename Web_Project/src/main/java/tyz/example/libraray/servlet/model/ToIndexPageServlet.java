package tyz.example.libraray.servlet.model;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tyz.example.libraray.Bean.book;
import tyz.example.libraray.Service.BookService;
import tyz.example.libraray.Service.iml.BookServiceiml;
import tyz.example.libraray.servlet.base.BaseServlet;
import tyz.example.libraray.servlet.base.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ToIndexPageServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查到所有图书数据
        BookService bs=new BookServiceiml();
        int id=1;
        String p=req.getParameter("id");
        if("".equals(p) || null==p){
            id=1;
        }
        else {
            id = (Integer.parseInt(req.getParameter("id")));
        }
        List<book> allBook = null;
        Integer k=0;
        try {
            k=bs.findAllBook().size();
            if(k<(id-1)*10){
                id=1;
            }
            else if(id<=0){
                id=(int)Math.ceil(1.0*k/10);
            }
            allBook = bs.find_book_page(id);
//            k=1/0;
            int totalPage= allBook.size()>0? (int)(Math.ceil(1.0 * k /10)) :1;
            //放到请求域中
            req.setAttribute("books",allBook);
            req.setAttribute("pages",totalPage);
            req.setAttribute("now_id",id);
            this.processTemplate("index",req,resp);

        } catch (Exception e) {
//            throw new RuntimeException(e);
            this.processTemplate("ErrorPage",req,resp);
        }

    }

}
