package org.nackademingroup.hotelbookingapp.services;

import org.nackademingroup.hotelbookingapp.models.Customer;
import org.nackademingroup.hotelbookingapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    public List<Customer> getMockCustomers() {
        List<Customer> mockCustomers = new ArrayList<>();
        mockCustomers.add(Customer.builder()
                .id(1L)
                .name("Antonio Larzon")
                .phone_number("123-456-7890")
                .build());
                
        mockCustomers.add(Customer.builder()
                .id(2L)
                .name("Viktor Jonzon")
                .phone_number("098-765-4321")
                .build());
        return mockCustomers;
    }
    
    public Optional<Customer> getCustomerById(Long id) {
        return getMockCustomers().stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }
    
    public Customer updateCustomer(Customer customer) {
        return customer;
    }
}
