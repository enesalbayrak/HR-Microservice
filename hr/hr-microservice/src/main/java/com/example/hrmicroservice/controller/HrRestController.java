package com.example.hrmicroservice.controller;

import com.example.hrmicroservice.dto.request.HireEmployeeRequest;
import com.example.hrmicroservice.dto.request.IncreaseSalaryRequest;
import com.example.hrmicroservice.dto.response.EmployeeResponse;
import com.example.hrmicroservice.service.HrService;
import com.example.hrmicroservice.validation.TcKimlikNo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequestScope
@Validated
@CrossOrigin

//Adapter -> HTTP Protocol <-mapping-> Java Class Methods

public class HrRestController {

    private HrService hrService;

    public HrRestController(HrService hrService) {
        this.hrService = hrService;
    }

    @GetMapping("{identityNo}")
    public EmployeeResponse getEmployeeInfo(@PathVariable @TcKimlikNo String identityNo){
        return hrService.findEmployeeByIdentity(identityNo);
    }

    @PostMapping
    public EmployeeResponse hireEmployee(@RequestBody @Validated HireEmployeeRequest request){
        return hrService.hireEmployee(request);
    }

    @DeleteMapping("{identityNo}")
    public EmployeeResponse fireEmployee (@PathVariable @TcKimlikNo String identityNo){
        return hrService.fireEmployee(identityNo);
    }

    @PutMapping
    public List<EmployeeResponse> increaseSalary(@RequestBody @Validated IncreaseSalaryRequest request){
        return hrService.updateSalaryInDepartment(request);
    }
}
