package org.example.repository;

import org.example.db.Database;
import org.example.dto.Book;
import org.example.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BookRepository {
    private final Database database;

    public BookRepository(Database database) {
        this.database = database;
    }

    public void createBook(Book book) {
        Connection con = database.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into book(title,author,amount,publish_year)" +
                            "values (?,?,?,?);");
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDouble(3, book.getAmount());
            preparedStatement.setDate(4,  Date.valueOf(book.getPublishYear()));
            int effectRows = preparedStatement.executeUpdate();
            System.out.println(effectRows);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    public List<Book> getByBookList() {
        Connection con = database.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("select * from book ");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Book> bookList = new LinkedList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setAmount(resultSet.getDouble("amount"));
                book.setPublishYear(resultSet.getDate("publish_year").toLocalDate());
                book.setVisible(resultSet.getBoolean("visible"));
                bookList.add(book);
            }
            con.close();
            return bookList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Book getBookById(int id) {
        try {
            Connection con = database.getConnection();
            Book book = null;
            PreparedStatement preparedStatement = con.prepareStatement("select * from book where id =?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setAmount(resultSet.getDouble("amount"));
                book.setPublishYear(resultSet.getDate("publish_year").toLocalDate());
                book.setVisible(resultSet.getBoolean("visible"));
            }
            con.close();
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateBookAmount(double amount, int id) {
        System.out.println(amount+id);
            try {
                Connection con = database.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement("update book set amount =? where id =?");
                preparedStatement.setDouble(1, amount);
                preparedStatement.setInt(2, id);
                int effectRows = preparedStatement.executeUpdate();
                System.out.println(effectRows);
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public void removeBookId(int id) {
        Connection con = database.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("delete from book where  id =? ");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
