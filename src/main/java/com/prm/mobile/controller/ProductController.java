package com.prm.mobile.controller;

import com.prm.mobile.dto.ProductDto;
import com.prm.mobile.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/popular")
    public ResponseEntity<List<ProductDto>> getPopularProducts() {
        return new ResponseEntity<>(productService.getPopularProduct(), HttpStatus.OK);
    }
}
