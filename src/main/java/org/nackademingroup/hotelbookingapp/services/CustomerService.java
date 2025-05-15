package org.nackademingroup.hotelbookingapp.services;

import org.nackademingroup.hotelbookingapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
}
