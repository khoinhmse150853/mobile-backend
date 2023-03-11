package com.prm.mobile.controller;

import com.prm.mobile.dto.CartItemDto;
import com.prm.mobile.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@Valid @RequestBody List<CartItemDto> cartItemDto) {
        orderService.createOrder(cartItemDto);
        return new ResponseEntity<>("Create Successfully!", HttpStatus.CREATED);
    }
}
