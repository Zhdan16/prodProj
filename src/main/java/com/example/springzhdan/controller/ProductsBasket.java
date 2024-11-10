package com.example.springzhdan.controller;

import com.example.springzhdan.enity.Basket;
import com.example.springzhdan.enity.OrderProducts;
import com.example.springzhdan.enity.Product;
import com.example.springzhdan.repository.*;
import com.example.springzhdan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "")
public class ProductsBasket {
    private final ProductRepository productRepository;
    public final UserService userService;
    public final UserRepository userRepository;
    public final BasketRepository basketRepository;
    @GetMapping(path = "/basket")
    public String basket(Model model) {
        OrderProducts orderProducts = new OrderProducts();
        model.addAttribute("ord", orderProducts);
        model.addAttribute("basket", basketRepository.prods(userService.getCurrentUser().getId()));
        return "data_ht6";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public String basketAdd(@RequestParam(name = "tov_id") Long productId) {
        Product product = productRepository.getReferenceById(productId);
        Basket basket;
        if (basketRepository.bId(userService.getCurrentUser().getId(), productId) != null) {
            basket = basketRepository.getReferenceById(basketRepository.bId(userService.getCurrentUser().getId(), productId));
            basket.setCount(basketRepository.count(userService.getCurrentUser().getId(), product.getId()) + 1);
        } else {
            basket = new Basket();
            basket.setCount(1);
        }
        basket.setUser(userRepository.getReferenceById(userService.getCurrentUser().getId()));
        basket.setProduct(product);
        basketRepository.save(basket);


        return "redirect:/info?prod_id=" + productId;
    }
}
