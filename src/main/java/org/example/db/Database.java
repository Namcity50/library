package org.example.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
@Component
public class Database {
    //    public void createTableStudentBook() {
//        try {
//            String createStudentBook = "create table if not exists studentBook(" +
//                    "id serial primary key," +
//                    "student_id varchar(33) foreign key, " +
//                    "book_id integer foreign key," +
//                    "created_date date, " +
//                    "status varchar(20)," +
//                    "return_date date," +
//                    "duration_date," +
//                    "constraint student_fk foreign key (student_id)" +
//                    "references student(id)," +
//                    "constraint book_fk foreign key (book_id)" +
//                    "references book(id));";
//            Connection con = getConnection();
//            Statement statement = con.createStatement();
//            statement.executeUpdate(createStudentBook);
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//    public void createTableBook() {
//        try {
//            String bookTable = "create table if not exists book(" +
//                    "id serial primary key unique," +
//                    "title varchar(20) not null," +
//                    "author varchar(20)," +
//                    "amaunt double precision," +
//                    "publish_year date," +
//                    "visible boolean );";
//            Connection con = getConnection();
//            Statement statement = con.createStatement();
//            statement.executeUpdate(bookTable);
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//    public void createTableStudent() {
//        try {
//            String studentTable = "create table if not exists student(" +
//                    "id serial primary key, " +
//                    "name varchar(20) not null ," +
//                    "surname varchar(20)not null ," +
//                    "phone varchar(13)not null, " +
//                    "password character(32)not null, " +
//                    "status varchar(20)," +
//                    "role varchar(20)," +
//                    "created_date date," +
//                    "visible boolean );";
//            Connection con = getConnection();
//            Statement statement = con.createStatement();
//            statement.executeUpdate(studentTable);
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
    public  Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_library", "postgres", "nukus111");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void initTable() {
        String bookTable = "create table if not exists book(" +
                "id serial primary key," +
                "title varchar(20) not null," +
                "author varchar(20)," +
                "amount double precision," +
                "publish_year date," +
                "visible boolean default true );";

        String studentTable = "create table if not exists student(" +
                "id serial primary key," +
                "name varchar(20) not null ," +
                "surname varchar(20)not null ," +
                "phone varchar(13)not null , " +
                "status varchar(20) ," +
                "role varchar(20) ," +
                "created_date date ," +
                "visible boolean default true );";

        String studentBook = "create table if not exists studentBook(" +
                "id serial primary key," +
                "created_date date ," +
                "status varchar(20) ," +
                "return_date date ," +
                "duration_date date ," +
                "book_id integer ," +
                "student_id integer," +
                "constraint student_fk foreign key (student_id)" +
                "references student(id) ," +
                "constraint book_fk foreign key (book_id)" +
                "references book(id));";
        execute(bookTable);
        execute(studentTable);
        execute(studentBook);
    }

    public void execute(String sql) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
