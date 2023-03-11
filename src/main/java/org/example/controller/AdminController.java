package org.example.controller;

import org.example.repository.BookRepository;
import org.example.util.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    @Autowired
    private AdminBookController adminBookController;
    @Autowired
    private AdminStudentController adminStudentController;

    public void menuAdmin() {
        System.out.println("********Menu******");
        String menu = """
                1 -> Book List:
                2 -> Add Book:
                3 -> Delete Book:
                4 -> Student List
                5 -> Add Student:
                6 -> Delete student:
                7 -> Student Taken book:
                8 -> BookTaken History:
                """;
        System.out.println(menu);
    }

    public void start() {
        boolean flag = true;
        int act;
        while (flag) {
            menuAdmin();
            act = ScannerUtil.getScanner().nextInt();
            switch (act) {
                case 1 -> adminBookController.bookList();
                case 2 -> adminBookController.addBook();
                case 3 -> adminBookController.startDelete();
                case 4 -> adminStudentController.studentList();
                case 5 -> adminStudentController.addStudent();
                case 6 -> adminStudentController.deleteStudent();
                case 7 -> adminStudentController.studentTakenBookList();
                case 8 -> adminStudentController.studentBooksAll();
            }
        }
    }
}
