package com.example.hrmicroservice.repository;

import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hrmicroservice.document.EmployeeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeDocumentRepository extends MongoRepository<EmployeeDocument,String> {
    List<EmployeeDocument> findAllByDepartment(Department department);
}
