package com.example.springzhdan.controller;

import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import com.example.springzhdan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "")
public class ProductsPage {
    private final ProductRepository productRepository;
    public final UserService userService;


    @GetMapping(path = "/products")

    public String secondResource(Model model, @RequestParam(name = "filter", required = false) String category, @RequestParam(name = "page", required = false) Integer page) {
        if (page == null) {
            return "redirect:/products?page=1";
        }
        List<Product> productList = new ArrayList<>();
        Long userId = null;

        if (userService.getCurrentUser() != null) {
            userId = userService.getCurrentUser().getId();
            model.addAttribute("user", userService.getCurrentUser());
            model.addAttribute("ifadmin", userService.getCurrentUser().getAdmin());
        } else {
            model.addAttribute("user", null);
            model.addAttribute("ifadmin", false);
        }

        if (userId != null) {
            if (category == null || category.isEmpty()) {
                List<Product> products = productRepository.products(userId);
                model.addAttribute("products", products);
                model.addAttribute("pages", (products.size() + 9) / 10);
            } else {
                for (Product product : productRepository.products(userId)) {
                    if (product.getCategory().getName().toLowerCase().contains(category.toLowerCase())) {
                        productList.add(product);
                    }
                }
                model.addAttribute("tex", category);
                model.addAttribute("products", productList);
                model.addAttribute("pages", (productList.size() + 9) / 10);
            }
        } else {
            productList.addAll(productRepository.findAll());
            model.addAttribute("products", productList);
            model.addAttribute("pages", (productList.size() + 9) / 10);
        }

        model.addAttribute("page", page);
        return "data_ht";
    }
}




























