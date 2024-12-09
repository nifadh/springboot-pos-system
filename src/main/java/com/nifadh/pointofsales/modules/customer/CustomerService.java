package com.nifadh.pointofsales.modules.customer;

import com.nifadh.pointofsales.exception.DuplicateResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        final String LK_COUNTRY_CODE = "+94";
        Customer customer = customerMapper.customerRequestToCustomer(customerRequest, LK_COUNTRY_CODE);
        checkForDuplicateCustomer(customer.getPhone());
        customer = customerRepository.save(customer);
        return customerMapper.customerToCustomerResponse(customer);
    }

    public CustomerResponseList getAllCustomers() {
        List<Customer> customers = customerRepository.findByIsDeletedFalse();
        return customerMapper.customerListToCustomerResponseList(customers);
    }

    private void checkForDuplicateCustomer(String phone) {
        if (customerRepository.existsByPhone(phone)) {
            throw new DuplicateResourceException("Phone: " + phone + " already exists!");
        }
    }

}
