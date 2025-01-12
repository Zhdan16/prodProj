package com.example.springzhdan.repository;

import com.example.springzhdan.enity.Review;
import com.example.springzhdan.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select r from Review r where r.user.id =:user and r.product.id =:product")
    Review review (long user, long product);

    @Query("select s from User s where s.email =:name")
    User user (String name);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);
}
