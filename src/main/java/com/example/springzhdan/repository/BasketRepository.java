package com.example.springzhdan.repository;

import com.example.springzhdan.enity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    @Query("select b from Basket b where b.user.id=:user order by b.orderIndex")
    List<Basket> prods (long user);

    @Query("select b.count from Basket b where b.user.id=:user and b.product.id=:product")
    Integer count (long user, long product);

    @Query("select b.id from Basket b where b.user.id=:user and b.product.id=:product")
    Long bId (long user, long product);
}
