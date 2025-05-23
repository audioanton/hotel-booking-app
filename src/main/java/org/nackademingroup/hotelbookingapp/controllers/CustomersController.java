package org.nackademingroup.hotelbookingapp.controllers;

import org.nackademingroup.hotelbookingapp.dto.CustomerDto;
import org.nackademingroup.hotelbookingapp.models.Customer;
import org.nackademingroup.hotelbookingapp.services.service_implementations.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String createCustomer(Customer customer) {
        customerService.createCustomer(customer);
        return "redirect:/customers";
    }

    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable("id") Long id, Customer customer) {
        // TODO: Validation?
        customer.setId(id);
        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }

    @PostMapping("/customers/{id}/delete")
    public String deleteCustomer(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        if (!customerService.canDeleteCustomer(id)) {
            redirectAttributes.addFlashAttribute("error", "Cannot delete customer with existing bookings");
            return "redirect:/customers/" + id;
        }

        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}
