package com.ibm.springboot.demo.service;

import java.util.List;

import com.ibm.springboot.demo.model.Student;

public interface StudentService {
	Student getStudentById(Integer studentId);
    List<Student> getAllStudents();
    Student updateStudent(Student student);
    Student deleteStudent(Integer studentId);
    Student createStudent(Student student);
}
