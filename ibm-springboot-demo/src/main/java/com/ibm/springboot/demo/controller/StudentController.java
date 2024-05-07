package com.ibm.springboot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ibm.springboot.demo.model.Student;
import com.ibm.springboot.demo.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    StudentService Service;

 //   @Autowired
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Integer studentId) {
        return Service.getStudentById(studentId);
    }

    @GetMapping("/All")
    public List<Student> getAllStudents() {
        return Service.getAllStudents();
    }
}