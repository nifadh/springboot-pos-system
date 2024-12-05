package com.nifadh.pointofsales.modules.product.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    List<Brand> findByIsDeletedIsFalse();
    Boolean existsByNameEqualsIgnoreCase(String name);
}
