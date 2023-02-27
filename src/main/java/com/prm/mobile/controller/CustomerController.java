package com.prm.mobile.controller;

import com.prm.mobile.dto.CustomerDto;
import com.prm.mobile.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.addCustomer(customerDto), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<CustomerDto> loginCustomer(@RequestParam String email,
                                                     @RequestParam String password) {
        return new ResponseEntity<>(customerService.findUserByPasswordAndEmial(email, password), HttpStatus.OK);
    }
}
