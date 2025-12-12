package com.latptop.flexuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.latptop.flexuy.model.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
    
}
