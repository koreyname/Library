package com.example.demo.util;
import java.sql.*;
public class JDBC {
   public static Connection Conn() throws ClassNotFoundException, SQLException {
       Class.forName("com.mysql.cj.jdbc.Driver");
       Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test_mybatis?serverTimezone=UTC&rewriteBatchedStatements=true","tim","123456");
       return conn;
   }
   public static void CloseConn(Connection conn) throws SQLException {
       if(conn!=null) {
           conn.close();
       }
   }
}
