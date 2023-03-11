package org.example.service;

import org.example.dto.Book;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

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
}
