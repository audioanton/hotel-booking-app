package org.nackademingroup.hotelbookingapp.services.service_implementations;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nackademingroup.hotelbookingapp.dto.CustomerDto;
import org.nackademingroup.hotelbookingapp.HotelBookingAppApplication;
import org.nackademingroup.hotelbookingapp.models.Customer;
import org.nackademingroup.hotelbookingapp.repositories.CustomerRepository;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = HotelBookingAppApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CustomerServiceImpTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    Customer customer = Customer.builder().name("Zingo").phoneNumber("4").build();

    private final List<Customer> testCustomers = List.of(
            Customer.builder().name("Bingo").phoneNumber("1").build(),
            Customer.builder().name("Ringo").phoneNumber("2").build(),
            Customer.builder().name("Dingo").phoneNumber("3").build()
    );

    @BeforeEach
    public void setup() {
        customerRepository.deleteAll();
        customerRepository.saveAll(testCustomers);
    }

    @Test
    void toCustomerDto() {
        CustomerDto actualDto = customerService.toCustomerDto(customer);
        assertEquals(customer.getName(), actualDto.getName());
        assertEquals(customer.getPhoneNumber(), actualDto.getPhoneNumber());
    }

    @Test
    void createCustomer() {
        customerService.createCustomer(customer);
        assertEquals(4, customerRepository.count());
        assertTrue(customerRepository
                .findAll()
                .stream()
                .map(Customer::getName)
                .anyMatch(name -> name.equals(customer.getName())));
        assertTrue(customerRepository
                .findAll()
                .stream()
                .map(Customer::getPhoneNumber)
                .anyMatch(number -> number.equals(customer.getPhoneNumber())));
        assertFalse(customerRepository
                .findAll()
                .stream()
                .map(Customer::getName)
                .anyMatch(name -> name.equals("Wrong name")));
        assertFalse(customerRepository
                .findAll()
                .stream()
                .map(Customer::getPhoneNumber)
                .anyMatch(number -> number.equals("999")));
    }


    /*@Test
    void getCustomerById() {
        fail();
    }


    @Test
    void toCustomerDto() {
        fail();
    }*/

    @Test
    void getCustomerDtoById() throws Exception {
        List<Long> ids = testCustomers.stream().map(Customer::getId).toList();
        List<String> names = testCustomers.stream().map(Customer::getName).toList();
        List<String> numbers = testCustomers.stream().map(Customer::getPhoneNumber).toList();

        assertEquals(ids.get(0), customerService.getCustomerDtoById(ids.get(0)).get().getId());
        assertEquals(ids.get(1), customerService.getCustomerDtoById(ids.get(1)).get().getId());
        assertEquals(ids.get(2), customerService.getCustomerDtoById(ids.get(2)).get().getId());

        assertEquals(names.get(0), customerService.getCustomerDtoById(ids.get(0)).get().getName());
        assertEquals(names.get(1), customerService.getCustomerDtoById(ids.get(1)).get().getName());
        assertEquals(names.get(2), customerService.getCustomerDtoById(ids.get(2)).get().getName());

        assertEquals(numbers.get(0), customerService.getCustomerDtoById(ids.get(0)).get().getPhoneNumber());
        assertEquals(numbers.get(1), customerService.getCustomerDtoById(ids.get(1)).get().getPhoneNumber());
        assertEquals(numbers.get(2), customerService.getCustomerDtoById(ids.get(2)).get().getPhoneNumber());
    }

    @Test
    void toCustomer() {
        CustomerDto testDto = CustomerDto.builder()
                .id(testCustomers.get(0).getId())
                .name(testCustomers.get(0).getName())
                .phoneNumber(testCustomers.get(0).getPhoneNumber())
                .build();

        Customer actual = customerService.toCustomer(testDto);

        assertEquals(testCustomers.get(0).getId(), actual.getId());
        assertEquals(testCustomers.get(0).getName(), actual.getName());
        assertEquals(testCustomers.get(0).getPhoneNumber(), actual.getPhoneNumber());
    }

    @Test
    void deleteCustomer() throws Exception {
        assert(customerService.getCustomerDtos().stream()
                .map(CustomerDto::getId)
                .toList()
                .contains(testCustomers.get(0).getId()));

        customerService.deleteCustomer(testCustomers.get(0).getId());

        assert(!customerService.getCustomerDtos().stream()
                .map(CustomerDto::getId)
                .toList()
                .contains(testCustomers.get(0).getId()));
    }

    @Test
    void getCustomerDtos() {
        List<CustomerDto> expected = List.of(
                CustomerDto.builder()
                  .name(testCustomers.get(0).getName())
                  .id(testCustomers.get(0).getId())
                  .phoneNumber(testCustomers.get(0).getPhoneNumber())
                  .build(),
                CustomerDto.builder()
                        .name(testCustomers.get(1).getName())
                        .id(testCustomers.get(1).getId())
                        .phoneNumber(testCustomers.get(1).getPhoneNumber())
                        .build(),
                CustomerDto.builder()
                        .name(testCustomers.get(2).getName())
                        .id(testCustomers.get(2).getId())
                        .phoneNumber(testCustomers.get(2).getPhoneNumber())
                        .build()
        );

        List<CustomerDto> actual = customerService.getCustomerDtos();

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(1).getId(), actual.get(1).getId());
        assertEquals(expected.get(2).getId(), actual.get(2).getId());

        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(1).getName(), actual.get(1).getName());
        assertEquals(expected.get(2).getName(), actual.get(2).getName());

        assertEquals(expected.get(0).getPhoneNumber(), actual.get(0).getPhoneNumber());
        assertEquals(expected.get(0).getPhoneNumber(), actual.get(0).getPhoneNumber());
        assertEquals(expected.get(0).getPhoneNumber(), actual.get(0).getPhoneNumber());
    }

    @Test
    void updateCustomer() {
        String update = "Update";
        String name = customerRepository.findById(testCustomers.get(0).getId()).get().getName();
        String number = customerRepository.findById(testCustomers.get(0).getId()).get().getPhoneNumber();

        CustomerDto newDto = CustomerDto.builder()
                .id(testCustomers.get(0).getId())
                .name(update)
                .phoneNumber(update)
                .build();

        customerService.updateCustomer(newDto);

        String newName = customerRepository.findById(testCustomers.get(0).getId()).get().getName();
        String newNumber = customerRepository.findById(testCustomers.get(0).getId()).get().getPhoneNumber();

        assertNotEquals(name, newName);
        assertNotEquals(number, newNumber);
        assertEquals(update, newName);
        assertEquals(update, newNumber);
    }
}

