package com.example.springzhdan.repository;

import com.example.springzhdan.enity.Basket;
import com.example.springzhdan.enity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query("select b.product from Basket b where b.user.id=:user")
    List<Product> prods (long user);


}
