package com.service.catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.service.catalog.dto.category.CategoryDTO;
import com.service.catalog.entity.Category;
import com.service.catalog.repository.CategoryRepository;
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public  CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    public Category create(CategoryDTO  categoryDTO){
        Category category = new Category();
        category.setTitle(categoryDTO.getTitle());
        return categoryRepository.save(category);
    }
}
