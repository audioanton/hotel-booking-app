package org.nackademingroup.hotelbookingapp.services.service_interfaces;

import org.nackademingroup.hotelbookingapp.dto.CustomerDto;
import org.nackademingroup.hotelbookingapp.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public Optional<CustomerDto> getCustomerDtoById(Long id);

    public Customer updateCustomer(Customer customer);

    public CustomerDto toCustomerDto(Customer customer);
    public Customer toCustomer(CustomerDto customerDto);

    public List<CustomerDto> getCustomerDtos();

    public Customer createCustomer(Customer customer);

    public boolean canDeleteCustomer(Long id);

    public void deleteCustomer(Long id);
}
