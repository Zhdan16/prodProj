package com.example.springzhdan.repository;


import com.example.springzhdan.enity.Value;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValuesRepository extends JpaRepository<Value, Long> {
}
