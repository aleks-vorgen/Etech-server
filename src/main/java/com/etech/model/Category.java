package com.etech.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToMany
    @JoinTable(name = "child_category_list",
            joinColumns = @JoinColumn(name = "parent_category_id"),
            inverseJoinColumns = @JoinColumn(name = "child_category_id"))
    private List<Category> childCategories;
    private String title;
    private Date createDate;

    public Category(Category parentCategory, List<Category> childCategories) {
        this.childCategories = childCategories;
        this.title = title;
    }
}
