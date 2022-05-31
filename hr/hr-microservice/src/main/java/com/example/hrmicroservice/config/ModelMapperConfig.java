package com.example.hrmicroservice.config;

import com.example.hr.domain.Employee;
import com.example.hrmicroservice.document.EmployeeDocument;
import com.example.hrmicroservice.dto.request.HireEmployeeRequest;
import com.example.hrmicroservice.dto.response.EmployeeResponse;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    private static final Converter<HireEmployeeRequest,Employee>
            HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER = (context) -> {
        var request = context.getSource();
        return new Employee.Builder(request.getIdentity())
                .fullname(request.getFirstname(), request.getLastname())
                .iban(request.getIban())
                .salary(request.getSalary())
                .jobStyle(request.getJobStyle())
                .birthYear(request.getBirthYear())
                .photo(request.getPhoto())
                .department(request.getDepartment())
                .build();
    };
    private static final Converter<EmployeeDocument,Employee>
            EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER = (context) -> {
        var document = context.getSource();
        return new Employee.Builder(document.getIdentity())
                .fullname(document.getFirstname(), document.getLastname())
                .iban(document.getIban())
                .salary(document.getSalary())
                .jobStyle(document.getJobStyle())
                .birthYear(document.getBirthYear())
                .photo(document.getPhoto())
                .department(document.getDepartment())
                .build();
    };
    private static final Converter<Employee,EmployeeResponse>
            EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER = (context) -> {
        var employee = context.getSource();
        var fullname = employee.getFullname();
        var response = new EmployeeResponse();
        response.setIdentity(employee.getIdentity().getValue());
        response.setFirstname(fullname.getFirstName());
        response.setLastname(fullname.getLastName());
        response.setSalary(employee.getSalary().getValue());
        response.setIban(employee.getIban().value());
        response.setDepartment(employee.getDepartment());
        response.setPhoto(employee.getPhoto().getBase64Value());
        response.setBirthYear(employee.getBirthYear().getValue());
        response.setJobStyle(employee.getJobStyle());
        return response;
    };
    private static final Converter<Employee,EmployeeDocument>
            EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER = (context) -> {
        var employee = context.getSource();
        var fullname = employee.getFullname();
        var document = new EmployeeDocument();
        document.setIdentity(employee.getIdentity().getValue());
        document.setFirstname(fullname.getFirstName());
        document.setLastname(fullname.getLastName());
        document.setSalary(employee.getSalary().getValue());
        document.setIban(employee.getIban().value());
        document.setDepartment(employee.getDepartment());
        document.setPhoto(employee.getPhoto().getBase64Value());
        document.setBirthYear(employee.getBirthYear().getValue());
        document.setJobStyle(employee.getJobStyle());
        return document;
    };

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER,
                HireEmployeeRequest.class, Employee.class);
        modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER,
                Employee.class, EmployeeResponse.class);
        modelMapper.addConverter(EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER,
                EmployeeDocument.class, Employee.class);
        modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER,
                Employee.class, EmployeeDocument.class);
        return modelMapper;
    }
}
