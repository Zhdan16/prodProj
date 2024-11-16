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
    public String secondResource(Model model, @RequestParam(name = "filter", required = false) String category, @RequestParam(name = "page") Integer page) {

        List<Product> listList = new ArrayList<>();
        if (category == null || category.isEmpty()) {
            model.addAttribute("products", productRepository.products(userService.getCurrentUser().getId()));
            model.addAttribute("pages", ((productRepository.products(userService.getCurrentUser().getId()).size()+4)/ 5));
        } else {
            for (Product product : productRepository.products(userService.getCurrentUser().getId())) {
                if (product.getCategory().getName().toLowerCase().contains(category.toLowerCase())) {
                    listList.add(product);
                }
            }
            model.addAttribute("tex", category);
            model.addAttribute("products", listList);
            model.addAttribute("pages", ((listList.size()+4)/ 5));

        }
        model.addAttribute("user", userService.getCurrentUser());
        if (userService.getCurrentUser() != null){
            model.addAttribute("ifadmin", userService.getCurrentUser().getAdmin());
        }else {
            model.addAttribute("ifadmin", false);
        }

        model.addAttribute("page", page);
        return "data_ht";
    }

}




























