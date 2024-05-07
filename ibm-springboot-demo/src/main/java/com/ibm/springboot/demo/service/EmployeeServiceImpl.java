
package com.ibm.springboot.demo.service;

import java.util.List;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.ibm.springboot.demo.Repository.EmployeeRepository;
import com.ibm.springboot.demo.exception.EmployeeNotFoundException;
import com.ibm.springboot.demo.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EmployeeRepository employeeRepository;
	public void EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
	@Override
	public Page<Employee> getEmployeePages(Integer page, Integer size, String sortBy) {
		PageRequest pageable = PageRequest.of(page, size, Sort.by(sortBy));
		return employeeRepository.findAll(pageable);
	}
	@Override
	public List<Employee> getAllEmployees() {
		LOG.info("getAllEmployees");
		// what if the collection is empty ?
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(String employeeId) {
		LOG.info(employeeId);
		Optional<Employee> empOptional = employeeRepository.findById(employeeId);
		if (empOptional.isEmpty()) {
			String errorMessage = "Employee with the id " + employeeId + " is not found!";
			LOG.warn(errorMessage);
			throw new EmployeeNotFoundException(errorMessage);
		} else
			return empOptional.get();
	}

	@Override
	public List<Employee> getEmployeeByFirstName(String firstName) {
		LOG.info(firstName);
		List<Employee> empList = employeeRepository.findByFirstName(firstName);
		if (empList.isEmpty()) {
			String errorMessage = "Employee with firstName " + firstName + " is not found!";
			LOG.warn(errorMessage);
			throw new EmployeeNotFoundException(errorMessage);
		}
		return empList;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		LOG.info(employee.toString());
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
	    if (employee.getEmployeeId() == null) {
	        throw new IllegalArgumentException("Employee ID must not be null");
	    }
	    
	    LOG.info("Updating employee: {}", employee);
	    Employee emp = employeeRepository.findById(employee.getEmployeeId()).orElse(null);
	    
	    if (emp == null) {
	        throw new IllegalArgumentException("No employee found with the given ID");
	    }

	    if (employee.getFirstName() != null) {
	        emp.setFirstName(employee.getFirstName());
	    }

	    if (employee.getSalary() != null) {
	        emp.setSalary(employee.getSalary());
	    }
	    
	    if (employee.getEmail() != null) {
	        emp.setEmail(employee.getEmail());
	    }
	    
	    return employeeRepository.save(emp);
	}

	@Override
	public Employee deleteEmployee(String employeeId) {
		LOG.info(employeeId);
		Employee empToBeDeleted = this.getEmployeeById(employeeId);
		employeeRepository.deleteById(employeeId);
		return empToBeDeleted;
	}

	public Employee saveEmployee(Employee employee) {

		if (employee.getSalary() < 0) {
			throw new IllegalArgumentException("Salary cannot be negative");
		}
		
		if (!isValidEmail(employee.getEmail())) {
            throw new IllegalArgumentException("Invalid email");
        }
		return employee;
	}
	private boolean isValidEmail(String email) {
        // Your email validation logic using regular expressions or any other method
        // For simplicity, let's assume any non-null and non-empty string is valid
        return email != null && !email.isEmpty();
    }
	

}
