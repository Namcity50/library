package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.Book;
import org.example.dto.StudentBooks;
import org.example.enums.EnumStatus;
import org.example.repository.BookRepository;
import org.example.repository.StudentBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentBookRepository studentBookRepository;

    public void getBookList() {
        List<Book> bookList = bookRepository.getByBookList();
        for (Book book : bookList) {
            System.out.println(book.toString());
        }
    }

    public boolean deleteBookAmount(int id, double amount) {
        Book exist = bookRepository.getBookById(id);
        if (exist == null) {
            return false;
        }
        if (exist.getId() == id) {
            double d = exist.getAmount() - amount;
            bookRepository.updateBookAmount(d, id);
        }
        return true;
    }

    public boolean deleteBookId(int id) {
        Book exist = bookRepository.getBookById(id);
        if (exist == null) {
            return false;
        }
        if (exist.getId() == id) {
            bookRepository.removeBookId(id);
        }
        return true;
    }

    public void getBookStudent(Integer id, double amount, LocalDate localDate) {
        Book exist = bookRepository.getBookById(id);
        if (exist == null){
            System.out.println("Not found: ");
            return;
        }
        if (exist.getAmount() < amount){
            System.out.println("kitob soni ozroq" + exist.getAmount());
            return;
        }
        if (studentBookRepository.count(ComponentContainer.CURRENT_STUDENT.getId()) > 5){
            System.out.println("your book amount > 5");
            return;
        }
        if (exist.getAmount() == null){
            System.out.println("Finish book:");
            return;
        }
        if (exist.getId().equals(id)){
           int num = (int) (exist.getAmount() - amount);
            bookRepository.updateBookAmount(num,id);
        }
        StudentBooks studentBooks = new StudentBooks();
        studentBooks.setStudent_id(ComponentContainer.CURRENT_STUDENT.getId());
        studentBooks.setBook_id(exist.getId());
        studentBooks.setStatus(EnumStatus.TAKEN.name());
        studentBooks.setDuration(localDate);
        studentBooks.setReturnedDate(localDate);
        for (int i = 0; i < amount; i++) {
            studentBookRepository.saveBookStudent(studentBooks);
        }
    }
}
