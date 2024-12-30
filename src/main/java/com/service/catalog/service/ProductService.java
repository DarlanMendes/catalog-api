package com.service.catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.service.catalog.dto.product.ProductDTO;
import com.service.catalog.entity.Product;
import com.service.catalog.repository.ProductRepository;
@Service
public class ProductService {
    

    private ProductRepository productRepository;
    private CloudinaryService cloudinaryService;
    public ProductService(ProductRepository productRepository,CloudinaryService cloudinaryService ){
        this.productRepository =  productRepository;
        this.cloudinaryService =  cloudinaryService;
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product create(ProductDTO productDTO,  MultipartFile file){
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategoryId(productDTO.getCategoryId());
        if(file.isEmpty()){
            return productRepository.save(product);
        }
        String img = this.cloudinaryService.uploadFile(file, "produc");
        product.setImg(img);
        return productRepository.save(product);
    }
}
