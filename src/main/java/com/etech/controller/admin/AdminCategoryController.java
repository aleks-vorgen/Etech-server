package com.etech.controller.admin;

import com.etech.model.Category;
import com.etech.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/categories")
public class AdminCategoryController {
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/child")
    public List<Category> getChildCategories() {
        return categoryRepository.findAllByChildCategoriesEmpty();
    }
}
