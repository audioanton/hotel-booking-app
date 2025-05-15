package org.nackademingroup.hotelbookingapp.controllers;

import org.nackademingroup.hotelbookingapp.models.Customer;
import org.nackademingroup.hotelbookingapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
    public String customer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer";
    }
    
    @GetMapping("/customers/{id}")
    public String getCustomer(@PathVariable("id") Long id, Model model) {
        Optional<Customer> customerOpt = customerService.getCustomerById(id);
        
        if (customerOpt.isPresent()) {
            model.addAttribute("customer", customerOpt.get());
            return "customer";
        } else {
            // Customer not found, redirect to customers list
            return "redirect:/customers";
        }
    }
    
    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @ModelAttribute Customer customer, Model model) {
        // Set the ID from the path variable to ensure it's correct
        customer.setId(id);
        
        // Update the customer
        Customer updatedCustomer = customerService.updateCustomer(customer);
        model.addAttribute("customer", updatedCustomer);
        model.addAttribute("successMessage", "Customer updated successfully");
        
        return "customer";
    }
}
