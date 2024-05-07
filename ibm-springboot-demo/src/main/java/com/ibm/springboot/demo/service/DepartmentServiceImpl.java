package com.ibm.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.springboot.demo.Repository.DepartmentRepository;
import com.ibm.springboot.demo.exception.DepartmentNotFoundException;
import com.ibm.springboot.demo.model.Department;
import com.ibm.springboot.demo.model.Employee;
@Service
public class DepartmentServiceImpl implements DepartmentService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	DepartmentRepository departmentRepository;

	@Override
    public Department getDepartmentById(String departmentId) {
        LOG.info("Retrieving department with ID: " + departmentId);

        // Retrieve the department by ID
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);

        // Check if the department exists
        if (!departmentOptional.isPresent()) {
            LOG.warn("Department with ID '" + departmentId + "' not found");
            // Handle the scenario where the department is not found (e.g., throw an exception)
            throw new DepartmentNotFoundException("Department with ID '" + departmentId + "' not found");
        }

        return departmentOptional.get();
    }
	@Override
	public Department addDepartment(Department department) {
        LOG.info("Adding department: " + department);

        // Check if the department already exists
        if (departmentRepository.findById(department.getDepartmentId()).isPresent()) {
            String errorMessage = "Department with ID '" + department.getDepartmentId() + "' already exists!";
            LOG.warn(errorMessage);
            throw new DepartmentNotFoundException(errorMessage);
        }

        // Save the department to the repository
        return departmentRepository.save(department);
    }
	@Override
	public List<Department> getAllDepartments() {
        LOG.info("Retrieving all departments");
        
        // Retrieve all departments from the repository
        List<Department> departments = departmentRepository.findAll();
        
        // Optionally, you can check if the list is empty and handle it accordingly
        if (departments.isEmpty()) {
            LOG.warn("No departments found in the database");
            // Handle the scenario where the list of departments is empty (e.g., throw an exception)
            throw new DepartmentNotFoundException("No departments found in the database");
        }
        LOG.info("Get all methods invoked");
        return departments;
    }
	@Override
    public Department getDepartmentByName(String departmentName) {
        LOG.info("Retrieving department by name: " + departmentName);
        
        // Retrieve departments by name from the repository
        List<Department> departments = departmentRepository.findByDepartmentName(departmentName);
        
        // Optionally, you can check if the list is empty and handle it accordingly
        if (departments.isEmpty()) {
            LOG.warn("No departments found with name: " + departmentName);
            // Handle the scenario where no departments are found with the given name (e.g., throw an exception)
            throw new DepartmentNotFoundException("No departments found with name: " + departmentName);
        }
        
        return (Department) departments;
    }

	@Override
    public Department updateDepartment(Department department) {
        LOG.info("Updating department: " + department);

        // Check if the department exists
        Optional<Department> existingDepartmentOptional = departmentRepository.findById(department.getDepartmentId());
        if (!existingDepartmentOptional.isPresent()) {
            String errorMessage = "Department with ID '" + department.getDepartmentId() + "' does not exist!";
            LOG.warn(errorMessage);
            // Handle the scenario where the department is not found (e.g., throw an exception)
            throw new DepartmentNotFoundException(errorMessage);
        }

        // Update the existing department
        Department existingDepartment = existingDepartmentOptional.get();
        existingDepartment.setDepartmentName(department.getDepartmentName());
        existingDepartment.setLocation(department.getLocation());

        // Save the updated department to the repository
        return departmentRepository.save(existingDepartment);
    }

    @Override
    public Department deleteDepartment(String departmentId) {
        LOG.info("Deleting department with ID: " + departmentId);

        // Retrieve the department to be deleted
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (!departmentOptional.isPresent()) {
            String errorMessage = "Department with ID '" + departmentId + "' does not exist!";
            LOG.warn(errorMessage);
            // Handle the scenario where the department is not found (e.g., throw an exception)
            throw new DepartmentNotFoundException(errorMessage);
        }

        // Delete the department from the repository
        departmentRepository.deleteById(departmentId);

        return departmentOptional.get();
    }
}
