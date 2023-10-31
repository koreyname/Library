package tyz.example.libraray.Service.iml;

import tyz.example.libraray.Bean.*;
import tyz.example.libraray.Dao.Impl.BookDaoiml;
import tyz.example.libraray.Dao.Impl.OrderItermDaoImpl;
import tyz.example.libraray.Dao.Inteface.BookDao;
import tyz.example.libraray.Dao.Inteface.OrderDao;
import tyz.example.libraray.Dao.Impl.OrderDaoImpl;
import tyz.example.libraray.Dao.Inteface.OrderItermDao;
import tyz.example.libraray.Service.OrderService;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao od=new OrderDaoImpl();
    private OrderItermDao oid=new OrderItermDaoImpl();
    private BookDao bd=new BookDaoiml();
    @Override
    public String creatOrder(Cart cart, info user) throws SQLException {
        //创建订单信息并保存
        String ordersequence = "TYZ" + System.currentTimeMillis();
        Date date = new Date();
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = spf.format(date);
        Order order = new Order(null, ordersequence, format, cart.getTotalCount(), cart.getTotalAmount(), 0, user.getId());
        //创建n个订单信息并保存
//        调用Dao
    int k=0;
        //订单项目的保存
        Collection<CartItem> allConnection = cart.getAllConnection();
        for (CartItem ci:allConnection) {
            //对图书的库存和销量进行修改
            book boo = ci.getBoo();
            if(boo.getStock() - ci.getCount()>=0) {
                if(k<=0){
                od.addOrder(order);
                k++;
                }
                Integer id=od.findIdBySequence(ordersequence);
                OrderItem oi=new OrderItem(null,ci.getBoo().getBookName(),ci.getBoo().getPrice(),ci.getBoo().getImgPath(),ci.getCount(),ci.getAmount(),id,ordersequence);
                oid.addOrderIterm(oi);
                //对图书馆的销量进行修改操作
                boo.setStock(boo.getStock() - ci.getCount());
                boo.setSales(boo.getSales() + ci.getCount());
                bd.edit_book(boo);
            }
            else{
                return null;
            }
        }
        return ordersequence;
    }


    public List<Order> findAllOrder(Integer id) throws SQLException {
        return od.findAllorder(id);
    }
}
