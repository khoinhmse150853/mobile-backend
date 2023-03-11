package com.prm.mobile.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemDto {
    private int id;

    private String name;

    private long price;

    private String img;

    private int quantity;

    private String username;
}
