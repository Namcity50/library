package org.example.service;

import org.example.dto.Student;
import org.example.enums.EnumStatus;
import org.example.enums.EnumsRole;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public boolean addStudent(Student student) {
        Student exist = studentRepository.getByStudentPhone(student.getPhone());
        if (exist != null ) {
            return false;
        }
        student.setRole(EnumsRole.STUDENT.name());
        student.setStatus(EnumStatus.ACTIVE.name());
        student.setCreatedDate(LocalDate.now());
        student.setVisible(true);
        studentRepository.createStudent(student);
        return true;
    }

    public Student getProfile(String phone) {
        Student exist = studentRepository.getByStudentPhone(phone);
        if (exist == null){
            return null;
        }
        return exist;
    }

    public boolean getStudent(String phone) {
       Student exist = studentRepository.getByStudentPhone(phone);
        if (exist == null){
            return false;
        }
        else if (exist.getRole().equals(EnumsRole.STUDENT.name())){
            return true;
        }
        return false;
    }

    public void studentList() {
        List<Student> studentList = studentRepository.getStudentList();
        for (Student student : studentList){
            if (student.getRole().equals(EnumsRole.STUDENT.name()))
            System.out.println(student.toString());
        }
    }
    public boolean deleteAdminStudent(String phone) {
        Student exist = studentRepository.getByStudentPhone(phone);
        if (exist == null) {
            return false;
        }
        if (exist.getPhone().equals(phone)) {
            studentRepository.removeStudent(phone);
        }
        return true;
    }


}
