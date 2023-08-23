package com.example.salon.controller;

import com.example.salon.model.dto.CustomerDto;
import com.example.salon.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/client")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(customerService.create(customerDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CustomerDto>> getPage(CustomerDto customerDto, Pageable pageable) {
        return new ResponseEntity<>(customerService.getPage(customerDto, pageable), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.update(customerDto), HttpStatus.OK);
    }
}
