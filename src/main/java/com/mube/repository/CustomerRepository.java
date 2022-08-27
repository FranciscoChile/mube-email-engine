package com.mube.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mube.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    
}
