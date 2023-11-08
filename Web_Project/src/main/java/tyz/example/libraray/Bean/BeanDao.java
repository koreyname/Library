package tyz.example.libraray.Bean;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BeanDao extends BasedDao{
    public <T> T findbyOneThing(Class<T> clazz, String sql, Object... args) throws SQLException {
        return qr.query(sql, new BeanHandler<T>(clazz), args);
    }
    public int update(String sql, Object... args) throws SQLException {
        return qr.update(sql, args);
    }

    public <T> List<T> findAllBySomeThing(Class<T> clazz, String sql,Object ...args) throws SQLException {
         return qr.query(sql, new BeanListHandler<T>(clazz),args);
    }

}
