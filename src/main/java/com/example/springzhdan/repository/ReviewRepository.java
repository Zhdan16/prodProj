package com.example.springzhdan.repository;

import com.example.springzhdan.enity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
