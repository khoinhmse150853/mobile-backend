package com.prm.mobile.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tblOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private String userName;

    private int productId;

    private String createDate;

    private BigDecimal price;

    private BigDecimal total;

    private int quantity;
}
