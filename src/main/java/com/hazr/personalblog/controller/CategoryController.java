package com.hazr.personalblog.controller;


import com.hazr.personalblog.dto.CategoryDTO;
import com.hazr.personalblog.model.Category;
import com.hazr.personalblog.service.CategoryService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:5173")
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
    public void createCategory(@RequestBody CategoryDTO category) {
        categoryService.createCategory(category.getCategoryName());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handlePasswordDoesNotMatchException(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
