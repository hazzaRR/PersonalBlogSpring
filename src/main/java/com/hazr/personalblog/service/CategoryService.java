package com.hazr.personalblog.service;


import com.hazr.personalblog.model.Category;
import com.hazr.personalblog.repository.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(String categoryName) {
            Category newCategory = new Category(categoryName);
            categoryRepository.save(newCategory);
    }
}
