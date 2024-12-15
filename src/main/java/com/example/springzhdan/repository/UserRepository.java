package com.example.springzhdan.repository;

import com.example.springzhdan.enity.Review;
import com.example.springzhdan.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select r from Review r where r.user.id =:user and r.product.id =:product")
    Review r (long user, long product);

    @Query("select s from User s where s.email =: name")
    User s (String name);
}
