package tyz.example.libraray.Dao.Impl;

import tyz.example.libraray.Bean.BeanDao;
import tyz.example.libraray.Bean.OrderItem;
import tyz.example.libraray.Dao.Inteface.OrderItermDao;

import java.sql.SQLException;
import java.util.List;

public class OrderItermDaoImpl extends BeanDao implements OrderItermDao {
    @Override
    public void addOrderIterm(OrderItem orderItem) throws SQLException {
        String sql="insert into t_order_item values(null,?,?,?,?,?,?,?)";
        this.update(sql,orderItem.getBookName(),orderItem.getPrice(),orderItem.getImgPath(),orderItem.getItemCount(),orderItem.getItemAmount(),orderItem.getOrderId(),orderItem.getOrderSequence());
    }

    @Override
    public List<OrderItem> findAllIterm(String id) throws SQLException {
        String sql="item_id itemId,book_name bookName,price,img_path imgPath,item_count itemCount,item_amount itemAmount,order_id orderId,order_sequence orderSequence";
        String s="select "+sql+" from t_order_item where order_sequence=?";
        return this.findAllBySomeThing(OrderItem.class,s,id);
    }

    @Override
    public List<OrderItem> findUserIterm(String id) throws SQLException {
        String sql="item_id itemId,book_name bookName,price,img_path imgPath,item_count itemCount,item_amount itemAmount,order_id orderId,order_sequence orderSequence";
        String s="select "+sql+" from t_order_item where id= ?";
        return this.findAllBySomeThing(OrderItem.class,s,id);
    }
}
