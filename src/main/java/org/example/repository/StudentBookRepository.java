package org.example.repository;

import org.example.db.Database;
import org.example.dto.Book;
import org.example.dto.Student;
import org.example.dto.StudentBooks;
import org.example.enums.EnumStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
@Repository
public class StudentBookRepository {
    @Autowired
    private Database database;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<StudentBooks> getByTakenBookList(String taken) {
        Connection con = database.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("select * from studentBook where status =? ");
            preparedStatement.setString(1,taken);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<StudentBooks> studentBooksList = new LinkedList<>();
            while (resultSet.next()) {
                StudentBooks studentBooks = new StudentBooks();
                studentBooks.setId(resultSet.getInt("id"));
                studentBooks.setCreatedDate(resultSet.getDate("created_date").toLocalDate());
                studentBooks.setStatus(resultSet.getString("status"));
                studentBooks.setReturnedDate(resultSet.getDate("return_date").toLocalDate());
                studentBooks.setDuration(resultSet.getDate("duration_date").toLocalDate());
                studentBooks.setBook_id(resultSet.getString("book_id"));
                studentBooks.setStudent_id(resultSet.getString("student_id"));
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
    public int count(Integer studentId) {
        return jdbcTemplate.queryForObject("select count(*) from studentBook" +
                " where status = 'TAKEN' and student_id = " + studentId, Integer.class);
    }

    public void saveBookStudent(Book exist, Student currentStudent) {
            String sql = "insert into studentBook(created_date,status,return_date,duration_date,book_id,student_id)" +
                    " values (now(),'%s',now(),now(),%d,%d);";
            sql = String.format(sql,EnumStatus.TAKEN,currentStudent.getId(),exist.getId());
            int n = jdbcTemplate.update(sql);
            System.out.println(n);
        }

    public StudentBooks getBookId(Integer id) {
        String sql = "SELECT * FROM studentBook Where id =" + id;
        StudentBooks book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(StudentBooks.class));
        return book;
    }

    public void updateStatus(String name,Integer id) {
        String sql = "Update studentBook set status ='%s', return_date = '%s'  where id = %d";
        sql = String.format(sql, name, Date.valueOf(LocalDate.now()), id);
        int n = jdbcTemplate.update(sql);
        System.out.println(n);
    }

    public List<StudentBooks> returnBookList(Integer id) {
        String sql = "SELECT * FROM studentBook where student_id = " + id;
        List<StudentBooks> list = jdbcTemplate.queryForList(sql, StudentBooks.class);
        return list;

    }
}
