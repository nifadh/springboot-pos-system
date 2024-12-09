package com.nifadh.pointofsales.modules.customer;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Boolean existsByPhone(String phone);
    List<Customer> findByIsDeletedFalse();
}
