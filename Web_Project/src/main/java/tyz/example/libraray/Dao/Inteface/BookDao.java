package tyz.example.libraray.Dao.Inteface;

import tyz.example.libraray.Bean.book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    public List<book> findAllBook() throws SQLException;
    public book findById(int id) throws SQLException;
    public boolean AddBook(book b) throws SQLException;
    public boolean delete_book(int id) throws SQLException;
    public boolean edit_book(book book) throws SQLException;
    public List<book> find_book_price(double first, double last) throws SQLException;
    public List<book> find_book_name(String name) throws SQLException;
    public List<book> find_book_page(int id) throws SQLException;
}
