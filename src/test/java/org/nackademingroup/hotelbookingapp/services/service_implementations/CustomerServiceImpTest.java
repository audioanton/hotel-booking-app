package org.nackademingroup.hotelbookingapp.services.service_implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nackademingroup.hotelbookingapp.models.Customer;
import org.nackademingroup.hotelbookingapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CustomerServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerServiceImp customerServiceImp;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {
        customerRepository.deleteAll();
        customerRepository.saveAll(
                List.of(
                        Customer.builder().name("Bingo").id(1L).phoneNumber("1").build(),
                        Customer.builder().name("Ringo").id(2L).phoneNumber("2").build(),
                        Customer.builder().name("Dingo").id(3L).phoneNumber("3").build()
                )
        );
    }

    @Test
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
    }
}