package org.example.controller;

import org.example.service.BookService;
import org.example.service.StudentBookService;
import org.example.util.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {
    @Autowired
    private BookService bookService;
    @Autowired
    private StudentBookService studentBookService;
    public void menuStudent(){
        String menu = """
                1 -> Book List:
                2 -> Take Book: (kitob olish)
                3 -> Taken book: (Olib lekin qaytarmagan kitoblar) 
                4 -> Return book:
                5 -> History:
                6 -> Order book:
                0 -> Back:
                """;
        System.out.println(menu);
    }
    public void start(){
        boolean flag = true;
        int act;
        while (flag){
            menuStudent();
            act = ScannerUtil.getScanner().nextInt();
            switch (act){
                case 1 -> bookList();
                case 2 -> takeBook();
                case 3 -> takenBook();
                case 4 -> returnBook();
                case 5 -> studentBookList();
            }
        }
    }

    private void studentBookList() {
        studentBookService.returnBooksAll();
    }

    private void returnBook() {
        System.out.println("Book Id: ");
        Integer id = ScannerUtil.getScanner().nextInt();System.out.println("Book Id: ");
        studentBookService.returnBookStudent(id);
    }

    private void takenBook() {
        studentBookService.getAdminByStudentTakenBooks();
    }

    private void takeBook() {
        System.out.println("Book Id: ");
        Integer id = ScannerUtil.getScanner().nextInt();
        System.out.println("Amount Book: ");
        double amount = ScannerUtil.getScanner().nextInt();
        bookService.getBookStudent(id,amount);
    }

    private void bookList() {
      bookService.getBookList();
    }
}
