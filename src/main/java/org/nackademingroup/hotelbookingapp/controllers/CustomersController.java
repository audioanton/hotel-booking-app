package org.nackademingroup.hotelbookingapp.controllers;

import jakarta.validation.Valid;
import org.nackademingroup.hotelbookingapp.dto.CustomerDto;
import org.nackademingroup.hotelbookingapp.models.Customer;
import org.nackademingroup.hotelbookingapp.services.service_implementations.CustomerServiceImp;
import org.nackademingroup.hotelbookingapp.services.service_interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomersController {

    @Autowired
    private CustomerServiceImp customerService;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/customers")
    public String customer(Model model) {
        List<CustomerDto> customers = customerService.getCustomerDtos();
        System.out.println(customers);
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/customers/{id}")
    public String getCustomer(@PathVariable("id") Long id, Model model) {
        Optional<CustomerDto> customerOpt = customerService.getCustomerDtoById(id);
        
        if (customerOpt.isPresent()) {
            model.addAttribute("customer", customerOpt.get());
            return "customer";
        } else {
            return "redirect:/customers";
        }
    }

    @GetMapping("/customers/new")
    public String showNewCustomerForm(Model model) {
        CustomerDto newCustomer = CustomerDto.builder().build();
        model.addAttribute("customer", newCustomer);
        return "customer";
    }

    @PostMapping("/customers/create")
    public String createCustomer(@Valid CustomerDto customerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            model.addAttribute("customer", customerDto);
            model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            return "customer";
        }

        customerService.createCustomer(customerDto);
        return "redirect:/customers";
    }

    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @Valid CustomerDto customerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customer", customerDto);
            model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            return "customer";
        }

        customerDto.setId(id);
        customerService.updateCustomer(customerDto);
        return "redirect:/customers";
    }

    @PostMapping("/customers/{id}/delete")
    public String deleteCustomer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        if (bookingService.hasActiveBookings(id)) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete customer with existing bookings");
            return "redirect:/customers/" + id;
        }

        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
