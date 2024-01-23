package de.aittr.g_31_2_shop.controllers;

import de.aittr.g_31_2_shop.domain.CommonCustomer;
import de.aittr.g_31_2_shop.domain.CommonProduct;
import de.aittr.g_31_2_shop.domain.interfaces.Customer;
import de.aittr.g_31_2_shop.domain.interfaces.Product;
import de.aittr.g_31_2_shop.services.intefaces.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public Customer save(@RequestBody CommonCustomer customer) {
        return service.save(customer);

    }

    @GetMapping
    public List<Customer> getAllActiveCustomers () {
        return service.getAllActiveCustomers();
    }

}
