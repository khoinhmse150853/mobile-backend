package com.prm.mobile.service;

import com.prm.mobile.dto.CartItemDto;

import java.util.List;

public interface OrderService {
    void createOrder(List<CartItemDto> cartItemDtoList);
}
