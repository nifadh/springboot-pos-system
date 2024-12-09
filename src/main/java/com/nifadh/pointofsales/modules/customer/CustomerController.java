package com.nifadh.pointofsales.modules.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customer")
    public CustomerResponse addCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        return customerService.addCustomer(customerRequest);
    }

    @GetMapping("/customer")
    public CustomerResponseList getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
