package com.example.salon.service.impl;

import com.example.salon.model.dto.EmployeeDto;
import com.example.salon.model.entity.Customer;
import com.example.salon.model.entity.Employee;
import com.example.salon.model.mapper.EmployeeMapper;
import com.example.salon.repository.EmployeeRepository;
import com.example.salon.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Employee create(Employee employee) {
        employee = Employee.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .build();
//        EmployeeDto employeeDto = employeeMapper.toEmployeeDto(employee);
        return employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toEmployeeDto)
                .collect(Collectors.toList());
    }
}
