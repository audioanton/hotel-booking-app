package org.nackademingroup.hotelbookingapp.services.service_implementations;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nackademingroup.hotelbookingapp.dto.CustomerDto;
import org.nackademingroup.hotelbookingapp.models.Customer;
import org.nackademingroup.hotelbookingapp.repositories.CustomerRepository;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@Rollback
class CustomerServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    Customer customer = Customer.builder().name("Bingo").phoneNumber("1").build();
    CustomerDto customerDto = CustomerDto.builder().name("Bingo").phoneNumber("1").build();

    /*@BeforeEach
    public void setup() {
        customerRepository.deleteAll();
        customerRepository.saveAll(
                List.of(
                        Customer.builder().name("Bingo").phoneNumber("1").build(),
                        Customer.builder().name("Ringo").phoneNumber("2").build(),
                        Customer.builder().name("Dingo").phoneNumber("3").build()
                )
        );
    }*/

    @Test
    void toCustomerDto() {
        CustomerDto actualDto = customerService.toCustomerDto(customer);
        assertEquals(customer.getName(), actualDto.getName());
        assertEquals(customer.getPhoneNumber(), actualDto.getPhoneNumber());
    }

    /*@Test
    void getCustomerById() {
        fail();
    }

    @Test
    void updateCustomer() {
        fail();
    }

    @Test
    void toCustomerDto() {
        fail();
    }*/


}