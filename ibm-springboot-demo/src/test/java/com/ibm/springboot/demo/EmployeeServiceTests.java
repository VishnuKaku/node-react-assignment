package com.ibm.springboot.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.springboot.demo.Repository.EmployeeRepository;
import com.ibm.springboot.demo.model.Employee;
import com.ibm.springboot.demo.service.EmployeeServiceImpl;


@SpringBootTest
@ExtendWith(MockitoExtension.class)

public class EmployeeServiceTests {
	@MockBean
	private EmployeeRepository employeeRepository;
	@InjectMocks
	private EmployeeServiceImpl employeeServiceImp;

	@BeforeEach
	public void setUp() {

		java.util.List<Employee> empList = new ArrayList<>();
		empList.add(new Employee("101", "Sonu", 10.5));
		empList.add(new Employee("102", "Monu", 12.5));
		empList.add(new Employee("103", "Tonu", 11.5));
		when(employeeRepository.findAll()).thenReturn(empList);
		
	}
	
	@Test
	public void testAllEmps() {
		assertEquals(employeeServiceImp.getAllEmployees().size(), 3);
	}
	@BeforeAll
	public static void beforeAllStuff() {

	}

	@AfterAll
	public static void afterAllStuff() {

	}

	@Test
	public void testAllEmps2() {
		assertNotEquals(employeeServiceImp.getAllEmployees().size(), 4);
	}

	@Test
	void testAllEmpsTimes() {
		employeeRepository.findAll();
		employeeRepository.findAll();
		verify(employeeRepository, times(2)).findAll();
	}
	
	

}
