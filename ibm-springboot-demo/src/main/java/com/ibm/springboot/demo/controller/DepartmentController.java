package com.ibm.springboot.demo.controller;

import com.ibm.springboot.demo.model.Department;
import com.ibm.springboot.demo.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

 
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{departmentId}")
    public Department getDepartmentById(@PathVariable String departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping("/add")
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }
    
    @GetMapping("/name/{departmentName}")
    public List<Department> getDepartmentByName(@PathVariable String departmentName) {
        return (List<Department>) departmentService.getDepartmentByName(departmentName);
    }
    
    @PutMapping("/update")
    public Department updateDepartment(@RequestBody Department department) {
        return departmentService.updateDepartment(department);
    }
    
    @DeleteMapping("/{departmentId}")
    public Department deleteDepartment(@PathVariable String departmentId) {
        return departmentService.deleteDepartment(departmentId);
    }
}

