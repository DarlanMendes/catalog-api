package com.service.catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.service.catalog.dto.product.ProductDTO;
import com.service.catalog.entity.Product;
import com.service.catalog.repository.ProductRepository;
@Service
public class ProductService {
    

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository =  productRepository;
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product create(ProductDTO productDTO){
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategoryId(productDTO.getCategoryId());
        return productRepository.save(product);
    }
}
