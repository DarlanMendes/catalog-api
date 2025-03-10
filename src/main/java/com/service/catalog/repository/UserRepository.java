package com.service.catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.service.catalog.entity.User;
@Repository
public interface UserRepository extends MongoRepository<User, String>{
    User findByEmail(String email);
}
