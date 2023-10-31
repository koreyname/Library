package com.example.demo.Service;

import com.example.demo.Bean.book;
import com.example.demo.Dao.Dao;
import com.example.demo.Dao.DaoImpl;

import java.util.List;

public class ServiceImpl implements Service{
    Dao dao=new DaoImpl();
    @Override
    public List<book> selectAll() {
        return dao.FindAll();
    }
}
