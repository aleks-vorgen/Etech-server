package com.etech.controller;

import com.etech.model.Product;
import com.etech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("all")
    public List<Product> getProductList(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "producer", required = false) String producer) {
        if ((title == null || title.isBlank()) &&
                (producer == null || producer.isBlank()))
            return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        else
            return productRepository.findAllByTitleContainsIgnoreCaseOrProducerContainsIgnoreCase(title, producer);
    }

    @GetMapping("id={id}")
    public Product getProductById(
            @PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @GetMapping("category={category}")
    public List<Product> getProductListByCategoryTitle(
            @PathVariable String category) {
        return productRepository.findAllByCategory_Title(category);
    }
}
