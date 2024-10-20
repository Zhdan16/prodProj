package com.example.springzhdan.repository;

import com.example.springzhdan.enity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    @Query("select o from Orders o where o.user.id=:user")
    List<Orders> ord_det(long user);
}
