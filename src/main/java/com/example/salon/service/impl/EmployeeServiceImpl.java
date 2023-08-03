package com.example.salon.service.impl;

import com.example.salon.model.dto.EmployeeDto;
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
    public EmployeeDto create(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.save(Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .login(employeeDto.getLogin())
                .password(employeeDto.getPassword())
                .phone(employeeDto.getPhone())
                .email(employeeDto.getEmail())
                .build());
        return employeeMapper.toEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toEmployeeDto)
                .collect(Collectors.toList());
    }
}
