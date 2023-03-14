package org.example.repository;

import org.example.db.Database;
import org.example.dto.GetStudentBook;
import org.example.dto.StudentBooks;
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
            preparedStatement.setString(1, taken);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<StudentBooks> studentBooksList = new LinkedList<>();
            while (resultSet.next()) {
                StudentBooks studentBooks = new StudentBooks();
                studentBooks.setId(resultSet.getInt("id"));
                studentBooks.setCreatedDate(resultSet.getDate("created_date").toLocalDate());
                studentBooks.setStatus(resultSet.getString("status"));
                studentBooks.setReturnedDate(resultSet.getDate("return_date").toLocalDate());
                studentBooks.setDuration(resultSet.getDate("duration_date").toLocalDate());
                studentBooks.setBook_id(resultSet.getInt("book_id"));
                studentBooks.setStudent_id(resultSet.getInt("student_id"));
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
                studentBooks.setStudent_id(resultSet.getInt("student_id"));
                studentBooks.setBook_id(resultSet.getInt("book_id"));
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

    public void saveBookStudent(StudentBooks studentBooks) {
        String sql = "insert into studentBook(created_date,status,return_date,duration_date,book_id,student_id)" +
                " values (now(),'%s','%s','%s',%d,%d);";
        sql = String.format(sql, studentBooks.getStatus(),
                studentBooks.getReturnedDate(),
                studentBooks.getDuration(),
                studentBooks.getBook_id(),
                studentBooks.getStudent_id());
        int n = jdbcTemplate.update(sql);
        System.out.println(n);
    }

    public StudentBooks getBYBookID(Integer id, Integer stId) {
        String sql = "select * from studentBook where book_id = " + id +
                " and student_id = " + stId + " and status = 'TAKEN' limit 1 ";
        StudentBooks studentBooks = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(StudentBooks.class));
        return studentBooks;
    }

    public List<StudentBooks> getStudentBookInfoList() {
        String sql = " select * from studentBook ";
        List<StudentBooks> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StudentBooks.class));
        return list;
    }

    public void updateStatus(String name, Integer id, Integer stId) {
        String sql = "Update studentBook set status ='%s', return_date = '%s'" +
                " where book_id = " + id + " and student_id = " + stId;
        sql = String.format(sql, name, Date.valueOf(LocalDate.now()));
        int n = jdbcTemplate.update(sql);
        System.out.println(n);
    }

    public List<GetStudentBook> returnBookList(Integer id) {
        String sql = " select  s.name as name, b.title as title, b.author as author,sb.return_date as return_date, " +
                "sb.created_date as taken_date from studentBook sb inner join student as s on s.id = sb.student_id " +
                " inner join book as b on b.id = sb.book_id where b.id = " + id;
        List<GetStudentBook> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GetStudentBook.class));
        return list;
    }
}
