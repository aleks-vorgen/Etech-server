package com.etech.controller;

import com.etech.model.Product;
import com.etech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("all")
    public List<Product> getProductList() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}
