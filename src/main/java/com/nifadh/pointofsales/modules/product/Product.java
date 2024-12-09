package com.nifadh.pointofsales.modules.product;

import com.nifadh.pointofsales.modules.product.brand.Brand;
import com.nifadh.pointofsales.modules.product.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NaturalId
    private String itemCode;

    private String name;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "brand_id")
    private Brand brand;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private Double costPrice;

    private Double sellingPrice;

    private Boolean isDeleted;
}
