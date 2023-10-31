package tyz.example.libraray.Service;

import tyz.example.libraray.Bean.info;

import java.sql.SQLException;

public interface Userservice {
    /**
     * 注册功能
     * @return
     */
    boolean regist(info in);
    info login(String username,String pwd);
    info check_user(String name) throws SQLException;
    public int getNum() throws SQLException;
}
