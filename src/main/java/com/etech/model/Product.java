package com.etech.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToOne
    private Category category;
    private String title;
    private BigDecimal price;
    private short discount;
    private short amount;
    private String description;
    private String imgPath;

    public Product(Category category, String title, BigDecimal price,
                   short discount, short amount, String description, String imgPath) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.discount = discount;
        this.amount = amount;
        this.description = description;
        this.imgPath = imgPath;
    }
}
