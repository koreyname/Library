package tyz.example.libraray.Service.iml;

import tyz.example.libraray.Bean.OrderItem;
import tyz.example.libraray.Dao.Impl.OrderItermDaoImpl;
import tyz.example.libraray.Dao.Inteface.OrderItermDao;
import tyz.example.libraray.Service.OrderItermService;

import java.sql.SQLException;
import java.util.List;

public class OrderItermServiceImpl implements OrderItermService {
    private OrderItermDao oid=new OrderItermDaoImpl();

    @Override
    public List<OrderItem> FindAllIterm(String id) throws SQLException {
        return oid.findAllIterm(id);
    }

    @Override
    public List<OrderItem> FinduserIterm(String id) throws SQLException {
      return oid.findUserIterm(id);
    }
}
