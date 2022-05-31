package com.example.hrmicroservice.dto.request;

import com.example.hr.domain.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IncreaseSalaryRequest {
    @NotNull
    private Department department;
    @Positive
    private double rate;
}
