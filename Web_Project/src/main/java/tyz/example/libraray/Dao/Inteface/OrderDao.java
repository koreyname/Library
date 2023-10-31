package tyz.example.libraray.Dao.Inteface;

import tyz.example.libraray.Bean.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    public void addOrder(Order order) throws SQLException;
    public Integer findIdBySequence(String orderSequence) throws SQLException;
    public List<Order> findAllorder(Integer id) throws SQLException;
}
