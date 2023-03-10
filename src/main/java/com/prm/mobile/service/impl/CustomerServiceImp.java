package com.prm.mobile.service.impl;

import com.prm.mobile.dto.CustomerDto;
import com.prm.mobile.entity.Customer;
import com.prm.mobile.exception.ExistedEntityException;
import com.prm.mobile.exception.ResourceNotFoundException;
import com.prm.mobile.repository.CustomerRepository;
import com.prm.mobile.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ModelMapper mapper;

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer customer = mapToEntity(customerDto);

        if (getCustomerById(customer.getUserName()) != null) {
            throw new ExistedEntityException("Customer was existed", "400");
        }

        Customer newCustomer = customerRepository.save(customer);

        return mapToDTO(newCustomer);
    }

    @Override
    public Customer getCustomerById(String userName) {
        return customerRepository.findById(userName)
                .orElse(null);
    }
    @Override
    public CustomerDto findUserByPasswordAndEmial(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            throw new ResourceNotFoundException("Email or Pssword is empty", "400");
        }

        Customer customer = customerRepository.findByEmailAndPassword(email, password);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found", "400");
        }

        return mapToDTO(customer);
    }

    private CustomerDto mapToDTO(Customer customer) {
        return mapper.map(customer, CustomerDto.class);
    }

    private Customer mapToEntity(CustomerDto customerDto) {
        return mapper.map(customerDto, Customer.class);
    }
}
