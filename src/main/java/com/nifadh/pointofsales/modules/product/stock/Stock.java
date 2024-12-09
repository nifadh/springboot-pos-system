package com.nifadh.pointofsales.modules.product.stock;

import com.nifadh.pointofsales.modules.branch.Branch;
import com.nifadh.pointofsales.modules.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "branch_id")
    private Branch branch;

    private Integer quantity;

    private Double weight;
}