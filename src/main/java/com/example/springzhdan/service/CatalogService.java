package com.example.springzhdan.service;

import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CatalogService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ValuesRepository valuesRepository;
    @Autowired
    private OptionsRepository optionsRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    public BasketRepository basketRepository;
    @Autowired
    public OrderProductRepository orderProductRepository;

    public Product productSearch(Long prod_id){
        return productRepository.getReferenceById(prod_id);
    }
    public Review reviewSearch(Long prod_id){
        return reviewRepository.getReferenceById(prod_id);
    }
    public List<Review> reviewList(Long prod_id){
        return new ArrayList<>(reviewRepository.review(prod_id));
    }

    public List<Option> optionList(){
        return new ArrayList<>(optionsRepository.findAll());
    }

    public List<Value> valueList(Long prod_id){
        return new ArrayList<>(valuesRepository.value(prod_id));
    }

    public Integer rating(Long prod_id){
        return reviewRepository.rate(prod_id);
    }

    public Review rev(Long prod_id){
        return userRepository.review(userService.getCurrentUser().getId(), prod_id);
    }

    public ProductRepository saveProd(){
        return  productRepository;
    }
    public List<Product> findProd(){
        return productRepository.findAll();
    }
    public List<Product> prodRepositFind(){
        return productRepository.products();
    }
    public ReviewRepository saveRev(){
        return reviewRepository;
    }

    public List<Review> checking(boolean bool){
        return reviewRepository.check(bool);
    }
    public void deleteProd(Long prodId){
        Product product = productRepository.getReferenceById(prodId);

        List<Review> review = reviewRepository.findAll();
        for (Review r : review) {
            if (Objects.equals(r.getProduct().getId(), prodId)) {
                reviewRepository.delete(r);
            }
        }

        List<Value> values = valuesRepository.findAll();
        for (Value v : values) {
            if (Objects.equals(v.getProduct().getId(), prodId)) {
                valuesRepository.delete(v);
            }
        }

        List<Basket> baskets = basketRepository.findAll();
        for (Basket b : baskets) {
            if (Objects.equals(b.getProduct().getId(), prodId)) {
                basketRepository.delete(b);
            }
        }

        List<OrderProducts> orders = orderProductRepository.findAll();
        for (OrderProducts o : orders) {
            if (Objects.equals(o.getProduct().getId(), prodId)) {
                orderProductRepository.delete(o);
            }
        }

        productRepository.delete(product);
    }
}
