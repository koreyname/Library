package tyz.example.libraray.Bean;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 这个类主要是用来连接数据库的
 */
public class BasedDao{
    static DataSource ds;
    static QueryRunner qr;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        try {
            properties.load(BasedDao.class.getClassLoader().getResourceAsStream("druid.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        qr=new QueryRunner(ds);
    }

    public static Connection getCOnnection()  {
        Connection conn = threadLocal.get();
        if (conn == null) {
            try {
                conn = ds.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    public static void disConnection(){
        Connection conn = threadLocal.get();

        if (conn != null) {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        threadLocal.remove();
    }

    public static DataSource getDs() {
        return ds;
    }
}
