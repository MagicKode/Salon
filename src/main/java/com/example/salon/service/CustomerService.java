package com.example.salon.service;

import com.example.salon.model.dto.CustomerDto;
import com.example.salon.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Page<CustomerDto> getPage(Pageable pageable);
}
