package com.example.salon.controller;

import com.example.salon.configuration.annotation.RoleSecured;
import com.example.salon.model.dto.EmployeeDto;
import com.example.salon.model.enums.Role;
import com.example.salon.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @RoleSecured({Role.DIRECTOR})
    @PostMapping
    public ResponseEntity<EmployeeDto> create(EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.create(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }
}
