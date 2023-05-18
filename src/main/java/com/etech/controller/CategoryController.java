package com.etech.controller;

import com.etech.model.Category;
import com.etech.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("all")
    public List<Category> getCategoryList(
            @RequestParam(name = "title", required = false) String title) {
        if (title == null || title.isBlank())
            return categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        else
            return categoryRepository.findAllByTitleContainsIgnoreCase(title);
    }

    @GetMapping("{id}")
    public Category getCategoryById(
            @PathVariable Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
