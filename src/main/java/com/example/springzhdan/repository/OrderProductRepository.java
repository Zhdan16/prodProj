package com.example.springzhdan.repository;

import com.example.springzhdan.enity.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProducts, Long> {
    @Query("select o from OrderProducts o where o.order.user.id=:user")
    List<OrderProducts> order(long user);
}
