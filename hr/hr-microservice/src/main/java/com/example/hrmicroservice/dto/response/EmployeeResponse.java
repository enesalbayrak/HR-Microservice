package com.example.hrmicroservice.dto.response;

import com.example.hr.domain.Department;
import com.example.hr.domain.JobStyle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeResponse {
    private String  identity;
    private String firstname;
    private String lastname;
    private String iban;
    private double salary;
    private JobStyle jobStyle;
    private String photo;
    private Department department;
    private int birthYear;

    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "identity='" + identity + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", iban='" + iban + '\'' +
                ", salary=" + salary +
                ", jobStyle=" + jobStyle +
                ", department=" + department +
                ", birthYear=" + birthYear +
                '}';
    }
}
