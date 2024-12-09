package com.nifadh.pointofsales.modules.customer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper {
    public Customer customerRequestToCustomer(CustomerRequest customerRequest, String countryCode) {
        String phone = customerRequest.getPhone();
        phone = countryCode + phone.substring(phone.length() - 9);

        return Customer.builder()
                .name(customerRequest.getName())
                .phone(phone)
                .isDeleted(false)
                .build();
    }

    public CustomerResponse customerToCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .build();
    }

    public CustomerResponseList customerListToCustomerResponseList(List<Customer> customers) {
        List<CustomerResponse> customerList = new ArrayList<>();
        customers.forEach(customer -> {
            customerList.add(customerToCustomerResponse(customer));
        });
        return new CustomerResponseList(customerList);
    }


}
