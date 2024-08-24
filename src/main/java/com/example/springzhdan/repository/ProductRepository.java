package com.example.springzhdan.repository;

import com.example.springzhdan.enity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
