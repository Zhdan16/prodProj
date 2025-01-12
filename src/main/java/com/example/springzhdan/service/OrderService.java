package com.example.springzhdan.service;

import com.example.springzhdan.enity.Basket;
import com.example.springzhdan.enity.OrderProducts;
import com.example.springzhdan.enity.Orders;
import com.example.springzhdan.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    public  BasketRepository basketRepository;
    @Autowired
    public  OrderProductRepository orderProductsRepository;
    @Autowired
    public  OrderRepository orderRepository;
    @Autowired
    public  UserService userService;
    @Autowired
    public UserRepository userRepository;

    public List<OrderProducts> prodByUserId(){
        return orderProductsRepository.order(userService.getCurrentUser().getId());
    }

    OrderProducts productsOrderProducts;
    Orders order;
    public Orders placeANOrderUserInfo(){
        order = new Orders();
        productsOrderProducts = new OrderProducts();
        java.util.Date date = new java.util.Date();
        order.setUser(userService.getCurrentUser());
        order.setAddress(userService.address());
        order.setOrderDate(date);
        order.setStatus(1);
        return order;
    }

    public OrderProducts placeANOrderProductInfo(Long productId, Integer count){
        productsOrderProducts.setOrder(order);
        productsOrderProducts.setProduct(productRepository.getReferenceById(productId));
        productsOrderProducts.setCount(count);
        return productsOrderProducts;
    }

    public Basket delProd(Long productId){
        return basketRepository.getReferenceById(basketRepository.bId(userService.getCurrentUser().getId(), productId));
    }
}
