package com.example.hrmicroservice.config;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.StandardHrApplication;
import com.example.hr.infra.EventPublisher;
import com.example.hr.repository.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HrAppConfig {
    @Bean
    public HrApplication createHrApplication(EmployeeRepository employeeRepository, EventPublisher eventPublisher){
        return new StandardHrApplication(employeeRepository,eventPublisher);
    }
}
