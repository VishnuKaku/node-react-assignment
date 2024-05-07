package com.ibm.springboot.demo.model;

public class Student {
	private Integer studentId;
    private String name;
    private Double grade;

    public Student() {
    }

    public Student(Integer studentId, String name, Double grade) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", name=" + name + ", grade=" + grade + "]";
    }
}
