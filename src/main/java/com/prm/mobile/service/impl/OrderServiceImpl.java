package com.prm.mobile.service.impl;

import com.prm.mobile.dto.CartItemDto;
import com.prm.mobile.entity.Order;
import com.prm.mobile.exception.ResourceNotFoundException;
import com.prm.mobile.repository.OrderRepository;
import com.prm.mobile.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void createOrder(List<CartItemDto> cartItemDtoList) {

        if (cartItemDtoList.isEmpty()) {
            throw new ResourceNotFoundException("List cart not found", "400");
        }
        
        List<Order> orders = cartItemDtoList.stream()
                .map(this::mapToOrder)
                .collect(Collectors.toList());

        orderRepository.saveAll(orders);
    }

    private Order mapToOrder(CartItemDto cartItemDto) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return Order.builder()
                .createDate(simpleDateFormat.format(new Date()))
                .price(BigDecimal.valueOf(cartItemDto.getPrice()))
                .total(BigDecimal.valueOf(cartItemDto.getPrice() * cartItemDto.getQuantity()))
                .quantity(cartItemDto.getQuantity())
                .productId(cartItemDto.getId())
                .userName(cartItemDto.getUsername())
                .build();
    }
}
