package org.example.service;

import org.example.dto.StudentBooks;
import org.example.enums.EnumStatus;
import org.example.repository.StudentBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentBookService {
    @Autowired
    private StudentBookRepository studentBookRepository;

    public void getAdminByStudentTakenBooks() {
        List<StudentBooks> studentBooksList = studentBookRepository.getByTakenBookList(EnumStatus.TAKEN.name());
        for (StudentBooks studentBooks: studentBooksList){
            System.out.println(studentBooks.toString());
        }
    }

    public void getAdminBYAllStudentsBooks() {
        List<StudentBooks> studentBooksList = studentBookRepository.getStudentByBookList();
        for (StudentBooks studentBooks: studentBooksList){
            System.out.println(studentBooks.toString());
        }
    }

}
