package com.example.hrmicroservice.dto.request;

import com.example.hr.domain.*;
import com.example.hrmicroservice.validation.Iban;
import com.example.hrmicroservice.validation.TcKimlikNo;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HireEmployeeRequest {
    @TcKimlikNo
    private String  identity;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Iban
    private String iban;
    @Min(4_500)
    private double salary;
    @NotNull
    private JobStyle jobStyle;
    private String photo;
    @NotNull
    private Department department;
    @Max(2006)
    private int birthYear;

    @Override
    public String toString() {
        return "HireEmployeeRequest{" +
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
