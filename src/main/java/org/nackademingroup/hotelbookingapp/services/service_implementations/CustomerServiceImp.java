package org.nackademingroup.hotelbookingapp.services.service_implementations;

import org.nackademingroup.hotelbookingapp.dto.CustomerDto;
import org.nackademingroup.hotelbookingapp.models.Customer;
import org.nackademingroup.hotelbookingapp.repositories.CustomerRepository;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    public List<Customer> getMockCustomers() {
        /*
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

         */
        return null;
    }

    @Override
    public Optional<CustomerDto> getCustomerDtoById(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        return customerOpt.map(this::toCustomerDto);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> customerOpt = customerRepository.findById(customer.getId());
        if (customerOpt.isPresent()) {
            Customer existingCustomer = customerOpt.get();
            existingCustomer.setName(customer.getName());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            return customerRepository.save(existingCustomer);
        } else {
            return customer;
        }
    }

    @Override
    public CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }

    @Override
    public List<CustomerDto> getCustomerDtos() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            customerDtos.add(toCustomerDto(customer));
        }
        return customerDtos;
    }
}
