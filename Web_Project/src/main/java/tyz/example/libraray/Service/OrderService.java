package tyz.example.libraray.Service;

import tyz.example.libraray.Bean.Cart;
import tyz.example.libraray.Bean.Order;
import tyz.example.libraray.Bean.info;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    //处理创建订单业务
    public String creatOrder(Cart cart, info user) throws SQLException;
    public List<Order> findAllOrder(Integer id) throws SQLException;

}
