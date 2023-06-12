package com.etech.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class AdminProductDto {
    private String title;
    private Long category;
    private BigDecimal price;
    private String producer;
    private short discount;
    private short amount;
    private String description;
}
