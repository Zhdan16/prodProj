package com.example.springzhdan.repository;


import com.example.springzhdan.enity.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ValuesRepository extends JpaRepository<Value, Long> {
    @Query("select v from Value v where v.product.id=:product")
    List<Value> v(long product);
}
