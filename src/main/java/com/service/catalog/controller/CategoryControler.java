package com.service.catalog.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.catalog.dto.category.CategoryDTO;
import com.service.catalog.entity.Category;
import com.service.catalog.service.CategoryService;

@RequestMapping("category")
@RestController
public class CategoryControler {
    private CategoryService categoryService;
    public  CategoryControler(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @PostMapping
    public ResponseEntity<Category>  create(@RequestBody CategoryDTO  categoryDTO){
        return ResponseEntity.ok(categoryService.create(categoryDTO));
    }
}
