package com.hazr.personalblog.dto;

public class CategoryDTO {

    private String categoryName;


    public CategoryDTO() {
    }

    public CategoryDTO(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
