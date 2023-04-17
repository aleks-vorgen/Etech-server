package com.etech.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;
    private String title;
    private Date createDate;

    public Category(Category parentCategory, String title) {
        this.parentCategory = parentCategory;
        this.title = title;
    }
}
