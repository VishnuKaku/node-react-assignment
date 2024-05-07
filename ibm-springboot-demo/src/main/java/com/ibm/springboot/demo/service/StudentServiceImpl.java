package com.ibm.springboot.demo.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Service;
import com.ibm.springboot.demo.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
    private final List<Student> sl = new CopyOnWriteArrayList<>();
    Student s1 = new Student(1,"faf",99.1);
    Student s2 = new Student(2,"daf",99.2);
    Student s3 = new Student(3,"raf",99.3);
    Student s4 = new Student(4,"caf",99.4);
    Student s5 = new Student(5,"saf",99.5);
    Student s6 = new Student(6,"laf",99.6);
    Student s7 = new Student(7,"qaf",99.7);
    Student s8 = new Student(8,"waf",99.8);
    Student s9 = new Student(9,"yaf",99.9);
    Student s10 = new Student(10,"vaf",99.10);
    
    {
    	sl.add(s1);
    	sl.add(s2);
    	sl.add(s3);
    	sl.add(s4);
    	sl.add(s5);
    	sl.add(s6);
    	sl.add(s7);
    	sl.add(s8);
    	sl.add(s9);
    	sl.add(s10);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return sl.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Student> getAllStudents() {
        return sl;
    }

    @Override
    public Student updateStudent(Student student) {
        int index = sl.indexOf(getStudentById(student.getStudentId()));
        if (index != -1) {
            sl.set(index, student);
            return student;
        }
        return null;
    }

    @Override
    public Student deleteStudent(Integer studentId) {
        Student student = getStudentById(studentId);
        if (student != null) {
            sl.remove(student);
        }
        return student;
    }

    @Override
    public Student createStudent(Student student) {
        sl.add(student);
        return student;
    }
}

