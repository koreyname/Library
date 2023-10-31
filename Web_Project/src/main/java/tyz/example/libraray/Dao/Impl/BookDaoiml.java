package tyz.example.libraray.Dao.Impl;

import tyz.example.libraray.Bean.BeanDao;
import tyz.example.libraray.Bean.book;
import tyz.example.libraray.Dao.Inteface.BookDao;

import java.sql.SQLException;
import java.util.List;

public class BookDaoiml extends BeanDao implements BookDao {
    private  static  int count=0;
   private String sql=" id bookId,title bookName,author,price,sales,stock,img_path imgPath ";

    @Override
    public List<book> find_book_name(String name) throws SQLException {
            String sql="select "+this.sql+" from books where title like ?";
            String p="%"+name+"%";
            return this.findAllBySomeThing(book.class,sql,p);
    }

    @Override
    public List<book> find_book_page(int id) throws SQLException {
        int k=(id-1)*10;
        String sql="select "+this.sql+" from books limit ?,10";
        return this.findAllBySomeThing(book.class,sql,k);
    }

    @Override
    public List<book> findAllBook() throws SQLException {
        String sql="select "+this.sql+" from books";
        List<book>p=this.findAllBySomeThing(book.class,sql);
        count=p.size();
        return p;
    }
    @Override
    public boolean AddBook(book b) throws SQLException {
        String sql="insert into books values(?,?,?,?,?,?,?)";
        return this.update(sql,++count,b.getBookName(),b.getAuthor(),b.getPrice(),b.getSales(),b.getStock(),b.getImgPath())>0;
    }

    @Override
    public boolean delete_book(int id) throws SQLException {
        String s="delete from books where id=?";
        --count;
        return this.update(s,id)>0;
    }

    @Override
    public book findById(int id) throws SQLException {
        String sql="select "+this.sql+" from books where id = ?";
        return this.findbyOneThing(book.class,sql,id);
    }

    @Override
    public boolean edit_book(book book) throws SQLException {
        String sql="update books set title=?,author=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return this.update(sql,book.getBookName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getBookId())>0;
    }

    @Override
    public List<book> find_book_price(double first, double last) throws SQLException {
        String s="select "+this.sql+" from books where price>=? and price<=?";
        return this.findAllBySomeThing(book.class,s,first,last);
    }
}
