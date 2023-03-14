package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.dto.Order;
import org.example.service.BookService;
import org.example.service.StudentBookService;
import org.example.util.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
public class StudentController {
    @Autowired
    private BookService bookService;
    @Autowired
    private StudentBookService studentBookService;

    public void menuStudent() {
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

    public void start() {
        boolean flag = true;
        int act;
        while (flag) {
            menuStudent();
            act = ScannerUtil.getScanner().nextInt();
            switch (act) {
                case 1 -> bookList();
                case 2 -> takeBook();
                case 3 -> takenBook();
                case 4 -> returnBook();
                case 5 -> studentBookList();
                case 6 -> addOrder();
                case 0 -> flag = false;
                default -> System.out.println("Not found: ");
            }
        }
    }

    private void addOrder() {
        System.out.println("Your Order: ");
        String text = ScannerUtil.getScanner().nextLine();
        Order order = new Order();
        order.setStudentId(ComponentContainer.CURRENT_STUDENT.getId());
        order.setText(text);
        order.setCreatedDate(LocalDate.now());
        bookService.addOrder(order);
    }

    private void studentBookList() {
        studentBookService.returnBooksAll();
    }

    private void returnBook() {
        System.out.println("Book Id: ");
        Integer id = ScannerUtil.getScanner().nextInt();
        studentBookService.returnBookStudent(id);
    }

    private void takenBook() {
        studentBookService.getAdminByStudentTakenBooks();
    }

    private void takeBook() {
        System.out.println("Book Id: ");
        Integer id = ScannerUtil.getScanner().nextInt();
        System.out.println("Duration Date:");
        String durationDate = ScannerUtil.getScanner().nextLine();
        LocalDate localDate = LocalDate.now().plusDays(Long.parseLong(durationDate));
        bookService.getBookStudent(id, localDate);
    }

    private void bookList() {
        bookService.getBookList();
    }
}
