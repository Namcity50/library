package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.Book;
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
        StudentBooks exist = studentBookRepository.getBookId(id);
        Book book = bookRepository.getBookById(Integer.parseInt(exist.getStudent_id()));
        if (exist == null){
            System.out.println("not found: ");
            return;
        }
        if (exist.getStatus().equals(EnumStatus.TAKEN.name())){
            studentBookRepository.updateStatus(EnumStatus.RETURN.name(),id);
            bookRepository.updateBookAmount(book.getAmount()+1,id);
        }
    }

    public void returnBooksAll() {
        List<StudentBooks> studentBooksList = studentBookRepository.returnBookList
                (ComponentContainer.CURRENT_STUDENT.getId());
        for (StudentBooks studentBooks: studentBooksList){
            System.out.println(studentBooks.toString());
        }
    }

}
