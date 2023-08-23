package com.example.salon.service.impl;

import com.example.salon.model.dto.CustomerDto;
import com.example.salon.model.entity.Customer;
import com.example.salon.model.mapper.CustomerMapper;
import com.example.salon.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerMapper customerMapper;
    @InjectMocks
    CustomerServiceImpl testSubject;

    @Test
    void shouldCreate() {
        //given
        CustomerDto customerDto = createCustomer();
        when(customerRepository.save(customerMapper.toCustomer(customerDto))).thenReturn(any());
        //when
        CustomerDto result = testSubject.create(customerDto);
        //then
        Assertions.assertNotNull(result);
        verify(customerRepository, times(1)).save(customerMapper.toCustomer(customerDto));
    }

    private CustomerDto createCustomer() {
        return customerMapper.toCustomerDto(Customer.builder()
                .id("1")
                .firstName("Dima")
                .lastName("Zavadski")
                .phone("123456")
                .build());
    }
}
