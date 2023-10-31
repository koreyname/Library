package tyz.example.demo4.Dao;

import tyz.example.demo4.bean.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    public List<Car> selectAll(Statement statement) throws SQLException {
        String sql="select * from car";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Car>list=new ArrayList<>();
        while(resultSet.next()){
            int id=resultSet.getInt("id");
            String color=resultSet.getString("color");
            String brand=resultSet.getString("brand");
            double price=resultSet.getDouble("price");
            Car car=new Car(id,brand,color,price);
            list.add(car);
        }
        return list;
    }

}

