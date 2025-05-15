package org.nackademingroup.hotelbookingapp.controllers;

import org.nackademingroup.hotelbookingapp.models.Customer;
import org.nackademingroup.hotelbookingapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public String customer(Model model) {
        // Get mock customers from the service
        List<Customer> customers = customerService.getMockCustomers();
        
        // Add customers to the model to be used in the view
        model.addAttribute("customers", customers);
        
        return "customers";
    }
}
