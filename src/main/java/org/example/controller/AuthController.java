package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.dto.Student;
import org.example.repository.StudentRepository;
import org.example.service.StudentService;
import org.example.util.MD5Util;
import org.example.util.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentController studentController;
    @Autowired
    private AdminStudentController adminStudentController;
    @Autowired
    private AdminController adminController;

    public void menuSingIn(){
        System.out.println("********Menu*******");
        String menu = """
                1 -> Login:
                0 -> Exit:
                """;
        System.out.println(menu);
    }
    public void start(){
        boolean flag = true;
        int act;
        while (flag){
            menuSingIn();
            act = ScannerUtil.getScanner().nextInt();
            switch (act){
                case 1 -> singIn();
                case 0 -> flag = false;
                default ->  System.out.println("Not found: ");
            }
        }
    }
    private void singIn() {
        System.out.println("Phone Number:");
        String phone = ScannerUtil.getScanner().nextLine();
        boolean bAdmin = studentService.getAdmin(phone);
        boolean bStudent = studentService.getStudent(phone);
        if (bAdmin){
            adminController.start();
        }else if (bStudent){
            studentController.start();
        }
        System.out.println("Not Found: ");
    }
}
