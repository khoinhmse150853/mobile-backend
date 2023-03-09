package com.prm.mobile.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    private int productId;

    private String productName;

    private String productDescription;

    private String imageUrl;

    private String discount;

    private BigDecimal price;

    private int rating;

    private int categoryId;

    private String productType;
}
