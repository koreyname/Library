package tyz.example.libraray.Dao.Impl;

import tyz.example.libraray.Bean.BeanDao;
import tyz.example.libraray.Bean.info;
import tyz.example.libraray.Dao.Inteface.Dao;

import java.sql.SQLException;
import java.util.List;

public class USerDao extends BeanDao implements Dao {
    /**
     * 将用户信息保存到数据库操作
     *
     * @param in 传入的info对象
     * @return
     */
    public boolean addUser(info in) {
        String sql = "insert into users values(?,?,?,?)";
        int count = 0;
        try {
            count = this.update(sql, in.getId(),in.getName(), in.getPwd(), in.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count > 0;
    }

    @Override
    public info findbyName(String name) throws SQLException {
       String sql2="select * from users where name=?";
        return this.findbyOneThing(info.class,sql2,name);
    }

    @Override
    public int getCount() throws SQLException {
        String sql="select * from users";
        List<info> allBySomeThing = this.findAllBySomeThing(info.class, sql);
        return allBySomeThing.size();
    }
}
