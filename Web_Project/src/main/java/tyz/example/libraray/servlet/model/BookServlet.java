package tyz.example.libraray.servlet.model;

import org.apache.commons.beanutils.BeanUtils;
import tyz.example.libraray.Bean.book;
import tyz.example.libraray.Service.BookService;
import tyz.example.libraray.Service.iml.BookServiceiml;
import tyz.example.libraray.servlet.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BookServlet extends BaseServlet {
    private BookService bs = new BookServiceiml();

    protected void findAllBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {

        List<book> allBook = bs.findAllBook();
        req.setAttribute("books", allBook);
        this.processTemplate("manager/book_manager", req, resp);
    }

    protected void toAddBookPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        this.processTemplate("manager/book_add", req, resp);
    }

    protected void toAddBook(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException, SQLException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        book b = new book();
        BeanUtils.populate(b, parameterMap);
        boolean flag = bs.addbook(b);
        if (flag) {
            resp.sendRedirect(req.getContextPath() + "/book?flag=findAllBook");
        } else {
            req.getRequestDispatcher(req.getContextPath() + "/AddBook");
        }
    }
    protected void delete_book(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        boolean b = bs.delete_book(Integer.parseInt(req.getParameter("id")));
        if (b) {
            resp.sendRedirect(req.getContextPath() + "/book?flag=findAllBook");
        }
    }

    protected void EditPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        book b = bs.findById(id);
        req.setAttribute("book", b);
        this.processTemplate("/manager/book_edit", req, resp);
    }

    protected void Edit(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, SQLException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        book b = new book();
        BeanUtils.populate(b, map);
        boolean flag = bs.edit_book(b);
        if (flag) {
            response.sendRedirect(request.getContextPath() + "/book?flag=findAllBook");
        }
    }

    protected void FindBookByPrice(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String one=req.getParameter("first");
        String two=req.getParameter("last");
        double first,last;
        first=(one.isEmpty())?0.0:Double.parseDouble(one);
        last=(two.isEmpty())?0.0:Double.parseDouble(two);
            List<book> list = bs.find_book_price(first,last);
        req.setAttribute("books", list);
        req.setAttribute("pages",1);
        req.setAttribute("now_id",1);
        this.processTemplate("index", req, resp);
//        req.setAttribute("books",allBook);

//        ToIndexPageServlet indexPageServlet=new ToIndexPageServlet();
//        indexPageServlet.doPost(req, resp);
    }
    protected void FindBookByName(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
        String bookName = request.getParameter("bookName");
        List<book> list=null;
        List<book> alllbook=null;
        if (bookName == "") {
            alllbook=bs.findAllBook();
            list=bs.find_book_page(1);
            request.setAttribute("pages",1.0*alllbook.size()/10>0?(int)Math.ceil(1.0*alllbook.size()/10):1);
        } else {
            list = bs.find_book_name(bookName);
            request.setAttribute("pages",1.0*list.size()/10>0?(int)Math.ceil(1.0*list.size()/10):1);
        }
        request.setAttribute("books", list);
        request.setAttribute("now_id",1);
        this.processTemplate("index", request, response);
    }

}
