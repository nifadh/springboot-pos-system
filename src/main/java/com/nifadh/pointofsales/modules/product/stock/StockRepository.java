package com.nifadh.pointofsales.modules.product.stock;

import com.nifadh.pointofsales.modules.branch.Branch;
import com.nifadh.pointofsales.modules.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    Optional<Stock> findByProductAndBranch(Product product, Branch branch);
}
