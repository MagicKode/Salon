package com.example.salon.model.mapper;

import com.example.salon.model.dto.CustomerDto;
import com.example.salon.model.entity.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

//    @Mapping(source = "user.id", target = "id")
//    @Mapping(source = "user.login", target = "login")
//    @Mapping(source = "user.email", target = "email")
//    @Mapping(source = "customer.id", target = "id")
//    @Mapping(source = "customer.firstName", target = "firstName")
//    @Mapping(source = "customer.lastName", target = "lastName")
//    @Mapping(source = "customer.login", target = "login")
//    @Mapping(source = "customer.phone", target = "phone")
//    @Mapping(source = "customer.email", target = "email")
//    @Mapping(source = "customer.createdAt", target = "createdAt")
//    @Mapping(source = "customer.updatedAt", target = "updatedAt")
//    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "roles", ignore = true)
//    @Mapping(target = "isActive", ignore = true)
//    CustomerDto toCustomerUserDto(User user, Customer customer);
//
//    @Mapping(source = "id", target = "user.id")
//    @Mapping(source = "login", target = "user.login")
//    @Mapping(source = "email", target = "user.email")
//    @Mapping(source = "id", target = "customer.id")
//    @Mapping(source = "firstName", target = "customer.firstName")
//    @Mapping(source = "lastName", target = "customer.lastName")
//    @Mapping(source = "login", target = "customer.login")
//    @Mapping(source = "phone", target = "customer.phone")
//    @Mapping(source = "email", target = "customer.email")
//    @Mapping(source = "createdAt", target = "customer.createdAt")
//    @Mapping(source = "updatedAt", target = "customer.updatedAt")
//    @Mapping(target = "roles", ignore = true)
//    @Mapping(target = "isActive", ignore = true)
//    Customer toCustomerUser(CustomerDto customerDto, UserDto userDto);

    @Mapping(target = "password", ignore = true)
    CustomerDto toCustomerDto(Customer customer);

    Customer toCustomer(CustomerDto customerDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(CustomerDto customerDto, @MappingTarget Customer customer);

    // todo метод , который наоборот делает из одной DTO две сущности.




}
