package com.ibm.springboot.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.springboot.demo.model.Employee;
import com.ibm.springboot.demo.service.EmployeeService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("emp")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(path = "get-emp-pages", produces = "application/json")
	public ResponseEntity<Page<Employee>> getEmpPages(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "id") String sortBy) {
		Page<Employee> empList = employeeService.getEmployeePages(page, size, sortBy);
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "All employees data fetched successfully!");
		ResponseEntity<Page<Employee>> response = new ResponseEntity<Page<Employee>>(empList, headers, status);
		return response;
	}

	@GetMapping("get-all-emps")
	public ResponseEntity<List<Employee>> getAllEmps() {
		List<Employee> empList = employeeService.getAllEmployees();
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "All employees data fetched successfully!");
		ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(empList, headers, status);
		return response;
	}

	@GetMapping("get-emp-by-id/{eid}")
	public ResponseEntity<Employee> getEmpById(@PathVariable(name = "eid") String employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employee data fetched successfully!");
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(employee, headers, status);
		return response;
	}

	@GetMapping("get-emp-by-name/{fname}")
	public ResponseEntity<List<Employee>> getEmpName(@PathVariable(name = "fname") String firstName) {
		List<Employee> empList = employeeService.getEmployeeByFirstName(firstName);
		HttpStatus status = HttpStatus.OK;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employees data fetched successfully!");
		ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(empList, headers, status);
		return response;
	}

	@PostMapping("/add-emp")
	public ResponseEntity<Employee> addEmp(@RequestBody Employee employee, BindingResult result) {
		Employee empToBeAdded = employeeService.addEmployee(employee);
		HttpStatus status = HttpStatus.CREATED;
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", "Employee added successfully!");
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(empToBeAdded, headers, status);
		return response;
	}
	@PutMapping("/update-emp")
	public ResponseEntity<Employee> updateEmp(@Valid @RequestBody Employee employee, BindingResult result) {
		Employee updatedEmployee = employeeService.updateEmployee(employee);
		HttpHeaders headers = new HttpHeaders();
        headers.add("message", "Employee updated successfully!");
        return new ResponseEntity<>(updatedEmployee, headers, HttpStatus.OK);
	}
	@DeleteMapping("/delete-emp/{eid}")
	public ResponseEntity<Void> deleteEmp(@PathVariable(name = "eid") String employeeId) {
	    Employee isDeleted = employeeService.deleteEmployee(employeeId);
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("message", "Employee deleted successfully!");
	    return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
	}

	
//	    if (!isDeleted) {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
	
	
	
	
//	@PostMapping("/employees")
//    public ResponseEntity<String> createEmployee(@Valid @RequestBody Employee employee) {
//        employeeService.saveEmployee(employee);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully");
//    }
}

