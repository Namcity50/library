package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.dto.Student;
import org.example.enums.EnumsRole;
import org.example.service.StudentService;
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
        Student student = studentService.getProfile(phone);
        if (student.getRole().equals(EnumsRole.STUDENT.name())){
              ComponentContainer.CURRENT_STUDENT=student;
            studentController.start();
        }
        else if (student.getRole().equals(EnumsRole.ADMIN.name())) {
            adminController.start();
        }
        System.out.println("Not Found: ");
    }
}
