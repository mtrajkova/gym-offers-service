package com.bachelor.microservice1.api;

import model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("/message")
    public Employee test(@RequestHeader("first-request") String header) {
        System.out.println(header);

        Employee employee = new Employee();
        employee.setName("Mare");
        employee.setDesignation("developer");
        employee.setId("1");
        employee.setSalary(3000);

        return employee;
    }
}
