package org.example.repository;

import org.example.db.Database;
import org.example.dto.Book;
import org.example.dto.StudentBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
@Repository
public class StudentBookRepository {
    @Autowired
    private Database database;
    public List<StudentBooks> getByTakenBookList(String taken) {
        Connection con = database.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("select * from studentBook where =? ");
            preparedStatement.setString(1,taken);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<StudentBooks> studentBooksList = new LinkedList<>();
            while (resultSet.next()) {
                StudentBooks studentBooks = new StudentBooks();
                studentBooks.setId(resultSet.getInt("id"));
                studentBooks.setStudent_id(resultSet.getString("student_id"));
                studentBooks.setBook_id(resultSet.getString("book_id"));
                studentBooks.setStatus(resultSet.getString("status"));
                studentBooks.setCreatedDate(resultSet.getDate("created_date").toLocalDate());
                studentBooks.setDuration(resultSet.getDate("duration_date").toLocalDate());
                studentBooks.setReturnedDate(resultSet.getDate("return_date").toLocalDate());
                studentBooksList.add(studentBooks);
            }
            con.close();
            return studentBooksList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<StudentBooks> getStudentByBookList() {

        Connection con = database.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("select * from studentBook");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<StudentBooks> studentBooksList = new LinkedList<>();
            while (resultSet.next()) {
                StudentBooks studentBooks = new StudentBooks();
                studentBooks.setId(resultSet.getInt("id"));
                studentBooks.setStudent_id(resultSet.getString("student_id"));
                studentBooks.setBook_id(resultSet.getString("book_id"));
                studentBooks.setStatus(resultSet.getString("status"));
                studentBooks.setCreatedDate(resultSet.getDate("created_date").toLocalDate());
                studentBooks.setDuration(resultSet.getDate("duration_date").toLocalDate());
                studentBooks.setReturnedDate(resultSet.getDate("return_date").toLocalDate());
                studentBooksList.add(studentBooks);
            }
            con.close();
            return studentBooksList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
