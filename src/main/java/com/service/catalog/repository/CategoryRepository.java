package com.service.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.service.catalog.entity.Category;
@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    
} 