package org.example.controller;

import org.example.dto.Book;
import org.example.repository.BookRepository;
import org.example.service.BookService;
import org.example.util.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class AdminBookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;
    private void deleteBook() {
        String menu = """
                1 -> Delete Amount:
                2 -> Delete Id:
                0 -> Back:
                """;
        System.out.println(menu);
    }
    public void startDelete(){
        boolean flag = true;
        int act;
        while (flag){
            deleteBook();
            act = ScannerUtil.getScanner().nextInt();
            switch (act){
                case 1 -> deleteAmount();
                case 2 -> deleteID();
                case 0 -> flag =false;
            }
        }

    }

    private void deleteID() {
        System.out.println("Enter ID: ");
        int id = ScannerUtil.getScanner().nextInt();
        boolean b = bookService.deleteBookId(id);
    }

    public void deleteAmount() {
        System.out.println("Enter Book ID");
        int id = ScannerUtil.getScanner().nextInt();
        System.out.println("Enter Book Amount");
        double amount = ScannerUtil.getScanner().nextDouble();
        boolean b = bookService.deleteBookAmount(id,amount);
        if (!b){
            System.out.println("not Found: ");
        }
        System.out.println("successfully: ");
    }


    public void addBook() {
        System.out.println("Enter title: ");
        String title = ScannerUtil.getScanner().nextLine();
        System.out.println("Enter author: ");
        String author = ScannerUtil.getScanner().nextLine();
        System.out.println("Enter Publish_Year: ");
        String year = ScannerUtil.getScanner().nextLine();
        LocalDate localDate = LocalDate.of(Integer.valueOf(year),2,2);
        System.out.println("Enter Amount: ");
        Double amount = ScannerUtil.getScanner().nextDouble();
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishYear(localDate);
        book.setAmount(amount);
        bookRepository.createBook(book);
    }

    public void bookList() {
        bookService.getBookList();
    }

}
