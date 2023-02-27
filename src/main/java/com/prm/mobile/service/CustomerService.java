package com.prm.mobile.service;

import com.prm.mobile.dto.CustomerDto;
import com.prm.mobile.entity.Customer;

public interface CustomerService {
    CustomerDto addCustomer(CustomerDto customerDto);

    Customer getCustomerById(String userName);

    CustomerDto findUserByPasswordAndEmial(String email, String password);
}
