package com.hazr.personalblog.service;


import com.hazr.personalblog.exception.CategoryAlreadyExistsException;
import com.hazr.personalblog.model.Category;
import com.hazr.personalblog.repository.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void createCategory(String categoryName) throws CategoryAlreadyExistsException {

        Optional<Category> alreadyExists = categoryRepository.findByCategoryNameIgnoreCase(categoryName);

        if (alreadyExists.isPresent()) {
            throw new CategoryAlreadyExistsException("the category with the name " + categoryName + "already exists");
        }
            Category newCategory = new Category(categoryName);
            categoryRepository.save(newCategory);
    }
}
