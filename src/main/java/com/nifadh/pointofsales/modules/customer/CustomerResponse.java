package com.nifadh.pointofsales.modules.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class CustomerResponse {
    private final Integer id;
    private final String name;
    private final String phone;
}
