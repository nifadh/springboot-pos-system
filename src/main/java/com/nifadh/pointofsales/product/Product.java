package com.nifadh.pointofsales.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalId;


@Entity
@RequiredArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NaturalId
    private String itemCode;

    private String name;

    private String image;

    private String category;

    private Double costPrice;

    private Double sellingPrice;

    private Boolean isDeleted;
}
