package com.example.springzhdan.controller;

import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import com.example.springzhdan.service.CatalogService;
import com.example.springzhdan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ProductsPage {
    private final CatalogService catalogService;
    public final UserService userService;
    @GetMapping(path = "/products")

    public String secondResource(Model model, @RequestParam(name = "filter", required = false) String category, @RequestParam(name = "page", required = false) Integer page) {
//        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
//            User admin = new User();
//            admin.setEmail("admin@example.com");
//            admin.setPassword(passwordEncoder.encode("admin123"));
//            admin.setFirstName("Admin");
//            admin.setLastName("User");
//            admin.setAdmin(true);
//            userRepository.save(admin);
//
//        }
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

                model.addAttribute("products", catalogService.prodRepositFind());
                model.addAttribute("pages", (catalogService.prodRepositFind().size() + 9) / 10);
            } else {
                for (Product product : catalogService.prodRepositFind()) {
                    if (product.getCategory().getName().toLowerCase().contains(category.toLowerCase())) {
                        productList.add(product);
                    }
                }
                model.addAttribute("tex", category);
                model.addAttribute("products", productList);
                model.addAttribute("pages", (productList.size() + 9) / 10);
            }
        } else {
            productList.addAll(catalogService.findProd());
            model.addAttribute("products", productList);
            model.addAttribute("pages", (productList.size() + 9) / 10);
        }

        model.addAttribute("page", page);

        return "data_ht";
    }
}




























