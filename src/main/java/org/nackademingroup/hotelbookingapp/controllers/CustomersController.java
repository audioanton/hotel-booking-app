package org.nackademingroup.hotelbookingapp.controllers;

import org.nackademingroup.hotelbookingapp.models.Customer;
import org.nackademingroup.hotelbookingapp.services.service_implementations.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class CustomersController {

    @Autowired
    private CustomerServiceImp customerService;

    @GetMapping("/customers")
    public String customer(Model model) {
        List<Customer> customers = customerService.getMockCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/customers/{id}")
    public String getCustomer(@PathVariable("id") Long id, Model model) {
        Optional<Customer> customerOpt = customerService.getCustomerById(id);
        
        if (customerOpt.isPresent()) {
            model.addAttribute("customer", customerOpt.get());
            return "customer";
        } else {
            return "redirect:/customers";
        }
    }
}
