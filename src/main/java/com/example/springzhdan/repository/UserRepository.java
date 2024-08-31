package com.example.springzhdan.repository;

import com.example.springzhdan.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
