package tyz.example.libraray.Service.iml;

import tyz.example.libraray.Dao.Impl.USerDao;
import tyz.example.libraray.Service.Userservice;
import tyz.example.libraray.encoding.MD5Util;
import tyz.example.libraray.Bean.info;

import java.sql.SQLException;

public class Userserviceiml implements Userservice {
   private USerDao bd=new USerDao();
    /**
     * 先加密，后保存
     * @param in
     * @return
     */
    @Override
    public boolean regist(info in) {
       in.setPwd(MD5Util.encode(in.getPwd()));
       return bd.addUser(in);
    }

    @Override
    public info login(String username, String pwd){
        info in;
        try {
            in=bd.findbyName(username);
            if(in!=null){
               if(in.getPwd().equals(MD5Util.encode(pwd))){
                   return in;
               }
               else in=null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return in;
    }

    @Override
    public info check_user(String name) throws SQLException {
        return bd.findbyName(name);
    }

    @Override
    public int getNum() throws SQLException {
        return bd.getCount();
    }
}
