package org.nackademingroup.hotelbookingapp.services.service_implementations;

import org.nackademingroup.hotelbookingapp.dto.CustomerDto;
import org.nackademingroup.hotelbookingapp.models.Customer;
import org.nackademingroup.hotelbookingapp.repositories.CustomerRepository;
import org.nackademingroup.hotelbookingapp.repositories.BookingRepository;
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
    
    @Autowired
    private BookingRepository bookingRepository;


    @Override
    public Optional<CustomerDto> getCustomerDtoById(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        return customerOpt.map(this::toCustomerDto);
    }

    @Override
    public void createCustomer(CustomerDto customerDto) {
        customerRepository.save(this.toCustomer(customerDto));
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        Customer customer = this.toCustomer(customerDto);
        Optional<Customer> customerOpt = customerRepository.findById(customer.getId());
        if (customerOpt.isPresent()) {
            Customer existingCustomer = customerOpt.get();
            existingCustomer.setName(customer.getName());
            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            customerRepository.save(existingCustomer);
        } else {
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
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
    public Customer toCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .phoneNumber(customerDto.getPhoneNumber())
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
