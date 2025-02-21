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

import static java.lang.Math.ceil;

@Controller
@RequiredArgsConstructor
public class ProductsPage {
    private final CatalogService catalogService;
    public final UserService userService;
//    private final UserRepository userRepository;
//    public final PasswordEncoder passwordEncoder;
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


        if (userService.getCurrentUser() != null) {
            model.addAttribute("user", userService.getCurrentUser());
            model.addAttribute("ifadmin", userService.getCurrentUser().getAdmin());
        } else {
            model.addAttribute("user", null);
            model.addAttribute("ifadmin", false);
        }

        if (category == null || category.isEmpty()) {
            model.addAttribute("products", catalogService.prodRepositFind());
            model.addAttribute("pages", (int) Math.ceil(catalogService.prodRepositFind().size() / 6.0));

        } else {
            model.addAttribute("tex", category);
            if (!catalogService.filterProd(category).isEmpty()){
                model.addAttribute("products", catalogService.filterProd(category));
                model.addAttribute("pages", (int) Math.ceil(catalogService.filterProd(category).size() / 6.0));

            }else {
                model.addAttribute("products", catalogService.filterProd(category));
                model.addAttribute("pages", 0);
            }
        }
        model.addAttribute("page", page);
        return "data_ht";
    }
}




























