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

    @Override
    public Customer createCustomer(Customer client) {
        client = Customer.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .phone(client.getPhone())
                .build();
        return customerRepository.save(client);
    }

    @Override
    public Page<CustomerDto> getPage(Pageable pageable) {
        return getPage(pageable);
    }
}

