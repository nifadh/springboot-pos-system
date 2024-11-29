package com.nifadh.pointofsales.modules.product.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByIsDeletedIsFalse();
    Boolean existsByNameEqualsIgnoreCase(String category);
}
