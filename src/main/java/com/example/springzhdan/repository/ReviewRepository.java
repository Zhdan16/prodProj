package com.example.springzhdan.repository;

import com.example.springzhdan.enity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.product.id=:product and r.published")
    List<Review> r (long product);

     default Integer rate(long prod_id){
        int rate = 0;
        int sum = 0;
        for (Review review: r(prod_id)){
            rate += review.getRate();
            sum++;
        }

        return Math.round((float) rate/sum);
    }
}
