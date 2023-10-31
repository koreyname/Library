package com.example.demo.Dao;

import com.example.demo.Bean.book;
import com.example.demo.util.JDBC;
import com.mysql.cj.protocol.Resultset;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class DaoImpl implements Dao{
    Connection connection=null;
    {
        try {
            connection=JDBC.Conn();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<book> FindAll(){
        List<book> lb=new ArrayList<>();
        String sql="select * from book";
        try {
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                book b=new book(resultSet.getString("isbn"),resultSet.getString("book_name"),resultSet.getInt("price"));
                lb.add(b);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lb;
    }
}
