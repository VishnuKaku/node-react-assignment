package com.ibm.springboot.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.springboot.demo.model.Employee;
import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	// no need to define methods for basic CRUD operations
	public abstract List<Employee> findByFirstName(String firstName);

	public abstract Employee findByEmail(String email);

}
