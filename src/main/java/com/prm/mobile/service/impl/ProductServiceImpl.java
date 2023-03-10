package com.prm.mobile.service.impl;

import com.prm.mobile.dto.ProductDto;
import com.prm.mobile.entity.Product;
import com.prm.mobile.exception.ResourceNotFoundException;
import com.prm.mobile.repository.ProductRepository;
import com.prm.mobile.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    @Override
    public List<ProductDto> getPopularProduct() {
        List<Product> products = productRepository.findByProductType("Popular");

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("400", "Popular products mot found");
        }

        return products.stream().map(this::mapToDTO).toList();
    }

    @Override
    public List<ProductDto> getRecommendedProduct() {
        List<Product> products = productRepository.findByProductType("Recommend");

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("400", "Popular products mot found");
        }

        return products.stream().map(this::mapToDTO).toList();
    }

    @Override
    public List<ProductDto> getProductByCategory(int id) {
        List<Product> products = productRepository.findByCategoryId(id);

        if (products.isEmpty()) {
            throw new ResourceNotFoundException("400", "Products not found");
        }

        return products.stream().map(this::mapToDTO).toList();
    }

    @Override
    public ProductDto getProductById(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("400", "Product is not found"));
        return mapToDTO(product);
    }

    private ProductDto mapToDTO(Product product) {
        return mapper.map(product, ProductDto.class);
    }
}
