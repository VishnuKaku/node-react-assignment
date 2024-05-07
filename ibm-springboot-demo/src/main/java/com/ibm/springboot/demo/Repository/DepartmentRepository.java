package com.ibm.springboot.demo.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ibm.springboot.demo.model.Department;
@Repository
public interface DepartmentRepository extends MongoRepository<Department,String>{

	List<Department> findByDepartmentName(String departmentName);
//	List<Department> getAllDepartments();

//    Department getDepartmentById(String departmentId);
//
//    Department addDepartment(Department department);
}
