package tyz.example.libraray.Dao.Impl;

import tyz.example.libraray.Bean.BeanDao;
import tyz.example.libraray.Bean.Order;
import tyz.example.libraray.Dao.Inteface.OrderDao;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl extends BeanDao implements OrderDao {
    @Override
    public void addOrder(Order order) throws SQLException {
        String sql="insert into t_order values(null,?,?,?,?,?,?)";
        this.update(sql,order.getOrderSequence(),order.getCreateTime(),order.getTotalCount(),order.getTotalAmount(),order.getOrderStatus(), order.getUserId());
    }

    @Override
    public Integer findIdBySequence(String orderSequence) throws SQLException {
        String sql="order_id orderId,order_sequence orderSequence,create_time createTime,total_count totalCount,total_amount totalAmount,order_status orderStatus,user_id userId";
        String s="select "+sql+" from t_order where order_sequence=?";
       Order order=(Order)this.findbyOneThing(Order.class,s,orderSequence);
        return order.getUserId();
    }

    @Override
    public List<Order> findAllorder(Integer id) throws SQLException {
        String sql="order_id orderId,order_sequence orderSequence,create_time createTime,total_count totalCount,total_amount totalAmount,order_status orderStatus,user_id userId";
        String s="select "+sql+" from t_order where user_id=?";
        return this.findAllBySomeThing(Order.class,s,id);
    }
}
