package com.prm.mobile.service;

import com.prm.mobile.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getPopularProduct();

    List<ProductDto> getRecommendedProduct();
}
