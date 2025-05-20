package org.nackademingroup.hotelbookingapp.services.service_interfaces;

import org.nackademingroup.hotelbookingapp.dto.CustomerDto;
import org.nackademingroup.hotelbookingapp.models.Customer;

import java.util.Optional;

public interface CustomerService {
    public Optional<Customer> getCustomerById(Long id);

    public Customer updateCustomer(Customer customer);

    public CustomerDto toCustomerDto(Customer customer);
}
