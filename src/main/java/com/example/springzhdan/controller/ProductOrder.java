package com.example.springzhdan.controller;

import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import com.example.springzhdan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductOrder {
    private final ProductRepository productRepository;
    public final UserService userService;
    public final BasketRepository basketRepository;
    public final OrderProductRepository orderProductsRepository;
    public final OrderRepository orderRepository;

    @GetMapping(path = "/order")
    public String order(Model model) {
        model.addAttribute("order", orderProductsRepository.order(userService.getCurrentUser().getId()));
        return "data_ht7";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String ord(@RequestParam(name = "tov_id") Long productId, @RequestParam(name = "count") Integer count) {
        Orders order = new Orders();
        OrderProducts productsOrderProducts = new OrderProducts();
        java.util.Date date = new java.util.Date();
        order.setUser(userService.getCurrentUser());
        order.setAddress(userService.address());
        order.setOrderDate(date);
        order.setStatus(1);
        orderRepository.save(order);

        productsOrderProducts.setOrder(order);
        productsOrderProducts.setProduct(productRepository.getReferenceById(productId));
        productsOrderProducts.setCount(count);
        orderProductsRepository.save(productsOrderProducts);
        basketRepository.delete(basketRepository.getReferenceById(basketRepository.bId(userService.getCurrentUser().getId(), productId)));

        return "redirect:/basket";
    }
}
