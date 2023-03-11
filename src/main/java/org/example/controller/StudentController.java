package org.example.controller;

import org.example.util.ScannerUtil;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {
    public void menuStudent(){
        String menu = """
                1 -> Book List:
                2 -> Take Book: (kitob olish)
                3 -> Taken book: (Olib lekin qaytarmagan kitoblar) 
                4 -> Return book:
                5 -> History:
                6 -> Order book:
                """;
    }
    public void start(){
        boolean flag = true;
        int act;
        while (flag){
            menuStudent();
            act = ScannerUtil.getScanner().nextInt();
            switch (act){
                case 1 -> bookList();
            }
        }
    }

    private void bookList() {

    }
}
