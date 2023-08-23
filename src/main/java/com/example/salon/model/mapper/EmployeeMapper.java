package com.example.salon.model.mapper;

import com.example.salon.model.dto.EmployeeDto;
import com.example.salon.model.entity.Employee;
import com.example.salon.model.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

//    @Mapping(source = "user.id", target = "id")
//    @Mapping(source = "user.login", target = "login")
//    @Mapping(source = "user.email", target = "email")
//    @Mapping(source = "employee.id", target = "id")
//    @Mapping(source = "employee.firstName", target = "firstName")
//    @Mapping(source = "employee.lastName", target = "lastName")
//    @Mapping(source = "employee.login", target = "login")
//    @Mapping(source = "employee.phone", target = "phone")
//    @Mapping(source = "employee.email", target = "email")
//    @Mapping(source = "employee.createdAt", target = "createdAt")
//    @Mapping(source = "employee.updatedAt", target = "updatedAt")
//    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "roles", ignore = true)
//    @Mapping(target = "isActive", ignore = true)
//    EmployeeDto toEmployeeUserDto(User user, Employee employee);

//    @Mapping(source = "id", target = "user.id")
//    @Mapping(source = "login", target = "user.login")
//    @Mapping(source = "email", target = "user.email")
//    @Mapping(source = "id", target = "employee.id")
//    @Mapping(source = "firstName", target = "employee.firstName")
//    @Mapping(source = "lastName", target = "employee.lastName")
//    @Mapping(source = "login", target = "employee.login")
//    @Mapping(source = "phone", target = "employee.phone")
//    @Mapping(source = "email", target = "employee.email")
//    @Mapping(source = "createdAt", target = "employee.createdAt")
//    @Mapping(source = "updatedAt", target = "employee.updatedAt")
//    @Mapping(target = "roles", ignore = true)
//    @Mapping(target = "isActive", ignore = true)
//    Employee toEmployeeUser(EmployeeDto employeeDto);

    @Mapping(target = "password", ignore = true)
    EmployeeDto toEmployeeDto(Employee employee);

    Employee toEmployee(EmployeeDto employeeDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EmployeeDto employeeDto, @MappingTarget Employee employee);
}

//todo create userMapper