package org.nackademingroup.hotelbookingapp.controllers;

import org.nackademingroup.hotelbookingapp.models.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomersController {

    @GetMapping("/customers")
    public String customer(Model model) {
        return "customers";
    }
}
