package tyz.example.libraray.Service;

import tyz.example.libraray.Bean.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItermService {
    List<OrderItem> FindAllIterm(String id) throws SQLException;
    List<OrderItem> FinduserIterm(String id) throws SQLException;
}
