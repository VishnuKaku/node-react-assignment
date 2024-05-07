package com.ibm.springboot.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.springboot.demo.model.Employee;
import com.ibm.springboot.demo.service.EmployeeService;

import ch.qos.logback.core.util.Duration;

@SpringBootTest
class AppTests {
	//positivetestcase
//	@Test
//	public void testTest() {
//		Integer expected = 10;
//		Integer actual  = 5 + 5;;
//		assertEquals(expected, actual);
//	}
//	//negetive
//	@Test
//	public void testTest2() {
//		Integer unexpected = 11;
//		Integer actual  = 5 + 5;;
//		assertNotEquals(unexpected, actual);
//	}
	@Autowired
	private EmployeeService  employeeService;
	private static Logger LOG = LoggerFactory.getLogger(AppTests.class);
	
	@BeforeAll
	public void setUp() {
		LOG.info("before all");
	}
	@AfterAll
	public static void tearDown() {
		LOG.info("after all");
	}
	@ParameterizedTest
	@ValueSource(strings = { "Siddharth", "Yuvraj" })
	void testGetEmpsById(String employeeId) {
		assertEquals(employeeId, employeeService.getEmployeeByFirstName(employeeId).get(0).getFirstName());
	}

//	@Timeout(value = 10, unit = TimeUnit.MILLISECONDS)
//	@Test
//	public void testAllEmpsTimeout() {
//		assertTimeout(Duration.ofMillis(10), () -> {
//			employeeService.getAllEmployees();
//		});
//
//	}

//	@Test
//	public void testAddEmp() {
//		Employee emp = new Employee("asdf","Test Data","test@data.com",74243525559523L,90.9);
//		assertEquals(employeeService.addEmployee(emp),emp);
//	}
//	@Test
//	public void testAllEmps() {
//		assertEquals(employeeService.getAllEmployees().size(), 5);
//	}

//	@Test
//	public void testAllEmps2() {
//		assertNotEquals(employeeService.getAllEmployees().size(), 15);
//	}
// 
}

