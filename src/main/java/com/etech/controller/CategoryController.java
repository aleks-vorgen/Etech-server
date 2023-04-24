package com.etech.controller;

import com.etech.model.Category;
import com.etech.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Category> getCategoryList() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}
