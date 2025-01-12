package com.example.springzhdan.service;

import com.example.springzhdan.enity.Basket;
import com.example.springzhdan.enity.Product;
import com.example.springzhdan.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    public BasketRepository basketRepository;
    @Autowired
    public OrderProductRepository orderProductsRepository;
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public  UserService userService;
    @Autowired
    public UserRepository userRepository;


    public Basket ifNotBas(Long productId){
        Basket basket;
        Product product = productRepository.getReferenceById(productId);
        if (basketRepository.bId(userService.getCurrentUser().getId(), productId) != null) {
            basket = basketRepository.getReferenceById(basketRepository.bId(userService.getCurrentUser().getId(), productId));
            basket.setCount(basketRepository.count(userService.getCurrentUser().getId(), product.getId()) + 1);
        } else {
            basket = new Basket();
            basket.setCount(1);
            basket.setOrderIndex(basketRepository.prods(userService.getCurrentUser().getId()).size()+1);
        }
        basket.setUser(userRepository.getReferenceById(userService.getCurrentUser().getId()));
        basket.setProduct(product);
        return basket;
    }

    public Basket ifBas(Long productId, String operation){
        Product product = productRepository.getReferenceById(productId);
        Basket basket = basketRepository.getReferenceById(basketRepository.bId(userService.getCurrentUser().getId(), productId));
        if (operation.equals("+")){
            basket.setCount(basketRepository.count(userService.getCurrentUser().getId(), product.getId()) + 1);
        } else if (operation.equals("-") && basket.getCount()>1) {
            basket.setCount(basketRepository.count(userService.getCurrentUser().getId(), product.getId()) - 1);
        }
        return basket;
    }
}
