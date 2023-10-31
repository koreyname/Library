package tyz.example.libraray.Dao.Inteface;

import tyz.example.libraray.Bean.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItermDao {
    public void addOrderIterm(OrderItem orderItem) throws SQLException;
    public List<OrderItem> findAllIterm(String id) throws SQLException;
    public List<OrderItem> findUserIterm(String id) throws SQLException;
}
