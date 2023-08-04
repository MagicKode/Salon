package com.example.salon.service;

import com.example.salon.model.dto.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerDto create(CustomerDto customerDto);
    CustomerDto update(CustomerDto customerDto);

    Page<CustomerDto> getPage(CustomerDto customerDto, Pageable pageable);

    void deleteByName(String firstName, String lastName);
}
