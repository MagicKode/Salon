package com.example.salon.service.impl;

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
    public Page<CustomerDto> getPage(Pageable pageable) {
        return getPage(pageable);
    }
}

