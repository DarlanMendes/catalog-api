package com.service.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.service.catalog.entity.Product;
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    
}
