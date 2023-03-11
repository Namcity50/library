package org.example.controller;

import org.example.dto.Student;
import org.example.service.StudentBookService;
import org.example.service.StudentService;
import org.example.util.MD5Util;
import org.example.util.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminStudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentBookService studentBookService;

    public void studentList() {
        studentService.studentList();
    }

    public void addStudent() {
        System.out.println("Enter Name: ");
        String name = ScannerUtil.getScanner().nextLine();
        System.out.println("Enter SurName: ");
        String surName = ScannerUtil.getScanner().nextLine();
        System.out.println("Enter Phone: ");
        String phone = ScannerUtil.getScanner().nextLine();
        Student student = new Student();
        student.setName(name);
        student.setSurname(surName);
        student.setPhone(phone);
        boolean b = studentService.addStudent(student);
        if (!b) {
            System.out.println(" Exist Number: ");
        }
        System.out.println(" Successfully: ");
    }

    public void deleteStudent() {
        System.out.println("Enter Student Phone: ");
        String phone = ScannerUtil.getScanner().nextLine();
        boolean b = studentService.deleteAdminStudent(phone);
        if (!b) {
            System.out.println("not found: ");
        }
        System.out.println("successfully:");
    }

    public void studentTakenBookList() {
        studentBookService.getAdminByStudentTakenBooks();
    }

    public void studentBooksAll() {
        studentBookService.getAdminBYAllStudentsBooks();
    }
}
