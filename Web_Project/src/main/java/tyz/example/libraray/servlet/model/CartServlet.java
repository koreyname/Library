package tyz.example.libraray.servlet.model;

import com.google.gson.Gson;
import tyz.example.libraray.Bean.Cart;
import tyz.example.libraray.Bean.CartItem;
import tyz.example.libraray.Bean.book;
import tyz.example.libraray.Service.BookService;
import tyz.example.libraray.Service.iml.BookServiceiml;
import tyz.example.libraray.servlet.base.BaseServlet;
import tyz.example.libraray.servlet.base.ViewBaseServlet;
import tyz.example.libraray.servlet.util.CommonResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceiml();

    protected void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        //处理加入购物车的请求
        //1. 获得请求参数
        //获得图书的id值
        String id = request.getParameter("id");
        System.out.println("id = " + id);
        //获得到购物车的对象(到底有没有购物车对象-->去session中获取)
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");//这个key值，暂时不能确定
        if (cart == null) {
            //cart如果是null，说明session中没有Cart对象，新建一个Cart对象
            cart = new Cart();
            //新建完后，将对象存储在session中
            session.setAttribute("cart", cart);
        }
        //结论：程序运行到此处，cart就是购物车对象
        //2. 处理业务(不连数据库，所以没有service)
        //根据图书的id查到图书的信息
        book bookById = bookService.findById(Integer.parseInt(id));
        //应该将图书对象，传递到Cart类中，去判断Map集合中是否有这本书的信息
        cart.addCart(bookById);
        //3. 给响应
        //响应内容添加一个总数量(获得总数量)
        Integer totalCount = cart.getTotalCount();
        CommonResult ok = CommonResult.ok().setResultData(totalCount);
        String s = new Gson().toJson(ok);
        System.out.println("s = " + s);//{flag:true,resultData:3}
        response.getWriter().write(s);
    }

    protected void toCartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将页面跳转设置到cart.html
        this.processTemplate("cart/cart", request, response);
    }

    protected void showCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得到购物车的所有数据，然后响应给js
        //1. n个购物项   2. 总数量  3. 总金额
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //拿到的是Map集合中所有的value值！为什么不直接将Map集合拿到呢？因为要直接遍历
        Collection<CartItem> allCartItem = cart.getAllConnection();
        Integer totalCount = cart.getTotalCount();
        Double totalAmount = cart.getTotalAmount();
        //2. 需要将这些数据变为json字符串，返给js
        List list = new ArrayList();
        list.add(allCartItem);
        list.add(totalCount);
        list.add(totalAmount);
        CommonResult commonResult = CommonResult.ok().setResultData(list);
        String s = new Gson().toJson(commonResult);
        System.out.println("s = " + s);//该字符串就比较复杂了
        //{flag:true,resultData:[[{购物项},{购物项},{购物项}],5,500]}
        //这边需要将数据写回去
        response.getWriter().write(s);
    }

    protected void clearCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("cart");
        this.processTemplate("cart/cart", request, response);
    }

    protected void deleteCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getParameter("id");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        cart.deleteCartItem(Integer.parseInt(str));
        showCart(request, response);
    }

    protected void addCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.addCount(id);
        showCart(request, response);
    }

    protected void subCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.subCount(id);
        showCart(request, response);
    }

    protected void changeCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer count = Integer.parseInt(request.getParameter("count"));
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.changeCount(id, count);
        showCart(request, response);
    }


}
