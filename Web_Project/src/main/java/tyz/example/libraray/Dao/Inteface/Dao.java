package tyz.example.libraray.Dao.Inteface;

import tyz.example.libraray.Bean.info;

import java.sql.SQLException;

public interface Dao {
    //将User信息添加到数据库
    public boolean addUser(info in);
    public <T> info findbyName(String name) throws SQLException;
    public int getCount() throws SQLException;
}
