package com.example.salon.service;

import com.example.salon.model.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto create(EmployeeDto employeeDto);
    List<EmployeeDto> getAll();
}
