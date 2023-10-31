package tyz.example.libraray.Service.iml;

import tyz.example.libraray.Bean.book;
import tyz.example.libraray.Dao.Inteface.BookDao;
import tyz.example.libraray.Dao.Impl.BookDaoiml;
import tyz.example.libraray.Service.BookService;

import java.sql.SQLException;
import java.util.List;

public class BookServiceiml implements BookService {
   private BookDao bd=new BookDaoiml();
    @Override
    public List<book> findAllBook() throws SQLException {
        return bd.findAllBook();
    }

    @Override
    public boolean addbook(book b) throws SQLException {
        return bd.AddBook(b);
    }

    @Override
    public boolean delete_book(int id) throws SQLException {
        return bd.delete_book(id);
    }

    @Override
    public List<book> find_book_name(String name) throws SQLException {
        return bd.find_book_name(name);
    }

    @Override
    public book findById(int id) throws SQLException {
        return bd.findById(id);
    }

    @Override
    public boolean edit_book(book book) throws SQLException {
        return bd.edit_book(book);
    }

    @Override
    public List<book> find_book_page(int id) throws SQLException {
        return bd.find_book_page(id);
    }

    @Override
    public List<book> find_book_price(double first, double last) throws SQLException {
        return bd.find_book_price(first,last);
    }
}
