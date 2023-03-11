package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.db.Database;
import org.example.dto.Book;
import org.example.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Repository
public class StudentRepository {
    @Autowired
  private Database database;
     public void createStudent(Student student) {
        Connection con = database.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into student(name,surname,phone,status,role,created_date,visible)" +
                            "values (?,?,?,?,?,now(),?);");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setString(4, student.getStatus());
            preparedStatement.setString(5, student.getRole());
            preparedStatement.setBoolean(6,student.getVisible());
            int effectRows = preparedStatement.executeUpdate();
            System.out.println(effectRows);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public Student getByStudentPhone(String phone) {
        try {
            Connection con = database.getConnection();
            Student student = null;
            PreparedStatement preparedStatement = con.prepareStatement("select * from student where phone =?");
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setPhone(resultSet.getString("phone"));
                student.setStatus(resultSet.getString("status"));
                student.setRole(resultSet.getString("role"));
                student.setCreatedDate(resultSet.getDate("created_date").toLocalDate());

            }
            con.close();
            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Student> getStudentList() {

        Connection con = database.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("select * from student ");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> studentList = new LinkedList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setPhone(resultSet.getString("phone"));
                student.setStatus(resultSet.getString("status"));
                student.setRole(resultSet.getString("role"));
                student.setCreatedDate(resultSet.getDate("created_date").toLocalDate());
                student.setVisible(resultSet.getBoolean("visible"));
                studentList.add(student);
            }
            con.close();
            return studentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeStudent(String phone) {

        Connection con = database.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement
                    ("delete from student where  phone =? ");
            preparedStatement.setString(1, phone);
            preparedStatement.executeUpdate();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
