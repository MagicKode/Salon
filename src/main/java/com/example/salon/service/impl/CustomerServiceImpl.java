package com.example.salon.service.impl;

import com.example.salon.exception_handler.exception.BusinessException;
import com.example.salon.exception_handler.exception.ExceptionCodes;
import com.example.salon.model.dto.CustomerDto;
import com.example.salon.model.entity.Customer;
import com.example.salon.model.mapper.CustomerMapper;
import com.example.salon.repository.CustomerRepository;
import com.example.salon.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        Customer customer = customerRepository.save(Customer.builder()
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .login(customerDto.getLogin())
                .password(customerDto.getPassword())
                .phone(customerDto.getPhone())
                .email(customerDto.getEmail())
                .build());
        return customerMapper.toCustomerDto(customer);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        return null;
    }

    @Override
    public Page<CustomerDto> getPage(CustomerDto customerDto, Pageable pageable) {
        return getPage(customerDto, pageable);
    }

    @Override
    public void deleteByName(String firstName, String lastName) {
        Customer customer = findCustomerByFirstLastName(firstName, lastName);
        if (customer.getId() == null) {
            throw BusinessException.builder().code(ExceptionCodes.WRONG_DATA).arg("Can be deleted only when custom: TRUE").build();
        }
        customerRepository.delete(customer);
    }

    private Customer findCustomerByFirstLastName(String firstName, String lastName) {
        return customerRepository.findCustomerByFirstNameAndLastName(firstName, lastName);
    }
}

