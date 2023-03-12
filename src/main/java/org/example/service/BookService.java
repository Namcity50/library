package org.example.service;

import org.example.container.ComponentContainer;
import org.example.dto.Book;
import org.example.repository.BookRepository;
import org.example.repository.StudentBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void getBookStudent(Integer id, double amount) {
        Book exist = bookRepository.getBookById(id);
        if (exist == null){
            System.out.println("Not found: ");
        }
        if (exist.getAmount() < amount){
            System.out.println("kitob soni ozroq" + exist.getAmount());
        }
        for (int i = 0; i < amount; i++) {
            studentBookRepository.saveBookStudent(exist,ComponentContainer.CURRENT_STUDENT);
        }
    }
}
