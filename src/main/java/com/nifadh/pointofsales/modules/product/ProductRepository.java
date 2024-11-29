package com.nifadh.pointofsales.modules.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByIsDeletedIsFalse();
    Boolean existsByNameEqualsIgnoreCase(String name);
}
