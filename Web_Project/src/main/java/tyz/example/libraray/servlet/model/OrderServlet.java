package tyz.example.libraray.servlet.model;

import tyz.example.libraray.Bean.*;
import tyz.example.libraray.Service.OrderItermService;
import tyz.example.libraray.Service.OrderService;
import tyz.example.libraray.Service.iml.OrderItermServiceImpl;
import tyz.example.libraray.Service.iml.OrderServiceImpl;
import tyz.example.libraray.servlet.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@WebServlet("/Order")
public class OrderServlet extends BaseServlet {
    private OrderService os = new OrderServiceImpl();
    private OrderItermService ois=new OrderItermServiceImpl();

    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
            //获取结账相关数据
            //购物车数据,总数据，总金额
            String orderSequence = null;
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            if (cart != null) {
                info user = (info) request.getSession().getAttribute("user");
                //调用业务层
                //订单保存
                orderSequence = os.creatOrder(cart, user);
                //将订单号返回
                //清空购物车
                request.getSession().removeAttribute("cart");
            }
            request.setAttribute("orderSequence", orderSequence);
            this.processTemplate("/cart/checkout", request, response);
        }

    protected void toOrderPage(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
       info user= (info)request.getSession().getAttribute("user");
       //调用业务层
        List<Order> allOrder = os.findAllOrder(user.getId());
        request.setAttribute("orders",allOrder);
        this.processTemplate("order/order",request,response);
    }
    /**
     *  这个才是每一单的详情页
     */
    protected void getAllInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        //获取订单号
        String id=request.getParameter("id");
        //获取id所属的Sequence
        //调用订单业务层获取订单所有项
        List<OrderItem> allIterm = ois.FindAllIterm(id);
        request.setAttribute("allIterm",allIterm);
        this.processTemplate("order/orderItem",request,response);
    }

    /**
     * 根据用户找所有订单
     * @param request
     * @param response
     * @throws IOException
     * @throws SQLException
     */
    protected void getInfos(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        //获取订单号
        String id=request.getParameter("id");
        //获取id所属的Sequence
        //调用订单业务层获取订单所有项
        List<OrderItem> allIterm = ois.FinduserIterm(id);
        request.setAttribute("allIterm",allIterm);
        this.processTemplate("order/orderItem",request,response);
    }
}

