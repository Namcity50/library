package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.Book;
import org.example.dto.GetStudentBook;
import org.example.dto.StudentBooks;
import org.example.enums.EnumStatus;
import org.example.repository.BookRepository;
import org.example.repository.StudentBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentBookService {
    @Autowired
    private StudentBookRepository studentBookRepository;
    @Autowired
    private BookRepository bookRepository;

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
    public void returnBookStudent(Integer id) {
        StudentBooks studentBooks = studentBookRepository.getBYBookID(id,ComponentContainer.CURRENT_STUDENT.getId());
        if (studentBooks == null){
            System.out.println("not found: ");
        }
        if (studentBooks.getStatus().equals(EnumStatus.TAKEN.name())){
            studentBookRepository.updateStatus(EnumStatus.RETURN.name(),id,ComponentContainer.CURRENT_STUDENT.getId());
        }
    }
    public void returnBooksAll() {
        List<GetStudentBook> getStudentBookList = studentBookRepository.returnBookList
                (ComponentContainer.CURRENT_STUDENT.getId());
       for (GetStudentBook getStudentBook: getStudentBookList){
           System.out.println(getStudentBook.toString());
       }
    }

}
