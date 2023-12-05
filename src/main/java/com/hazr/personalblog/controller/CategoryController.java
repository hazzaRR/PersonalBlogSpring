package com.hazr.personalblog.controller;


import com.hazr.personalblog.model.Category;
import com.hazr.personalblog.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<Category> getAllCategories() {
        return categoryService.getCategories();
    }

    @PostMapping("/")
    public void createCategory(@RequestBody String categoryName) {
        categoryService.createCategory(categoryName);
    }
}
