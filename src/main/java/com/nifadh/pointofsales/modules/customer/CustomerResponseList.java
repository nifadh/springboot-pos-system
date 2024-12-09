package com.nifadh.pointofsales.modules.customer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class CustomerResponseList {
    private final List<CustomerResponse> customers;
}
