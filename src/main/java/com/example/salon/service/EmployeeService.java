package com.example.salon.service;

import com.example.salon.model.dto.EmployeeDto;
import com.example.salon.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee create(Employee employee);
    List<EmployeeDto> getAll();
}
