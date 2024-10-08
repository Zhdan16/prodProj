package com.example.springzhdan.repository;

import com.example.springzhdan.enity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.product.id=:product and r.published")
    List<Review> r (long product);

    @Query("select avg(r.rate) from Review r where r.product.id=:product")
    Integer rate (long product);
}
