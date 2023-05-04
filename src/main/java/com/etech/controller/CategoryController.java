package com.etech.controller;

import com.etech.model.Category;
import com.etech.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("all")
    public ResponseEntity<List<Category>> getCategoryList() {
        return ResponseEntity.ok(categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }
}
