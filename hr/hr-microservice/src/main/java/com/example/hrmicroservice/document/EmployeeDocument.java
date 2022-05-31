package com.example.hrmicroservice.document;

import com.example.hr.domain.Department;
import com.example.hr.domain.JobStyle;
import com.example.hrmicroservice.validation.Iban;
import com.example.hrmicroservice.validation.TcKimlikNo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Document(collection = "employees")
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDocument {
    @Id
    @TcKimlikNo
    private String  identity;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Indexed(unique = true)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDocument that = (EmployeeDocument) o;
        return identity.equals(that.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identity);
    }

    @Override
    public String toString() {
        return "EmployeeDocument{" +
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
