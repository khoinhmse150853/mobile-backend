package com.prm.mobile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

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
