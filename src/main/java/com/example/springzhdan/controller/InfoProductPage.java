package com.example.springzhdan.controller;

import com.example.springzhdan.enity.Option;
import com.example.springzhdan.enity.Product;
import com.example.springzhdan.enity.Review;
import com.example.springzhdan.enity.Value;
import com.example.springzhdan.repository.*;
import com.example.springzhdan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "")
public class InfoProductPage {
    private final ProductRepository productRepository;
    private final ValuesRepository valuesRepository;
    private final OptionsRepository optionsRepository;
    private final ReviewRepository reviewRepository;
    public final UserService userService;
    public final UserRepository userRepository;


    @GetMapping(path = "/info")
    public String infoProd(Model model, @RequestParam(name = "prod_id") Long prod_id) {

        Product product = productRepository.getReferenceById(prod_id);
        model.addAttribute("product", product);
        List<Review> reviewList = new ArrayList<>(reviewRepository.r(prod_id));
        List<Option> optionList = new ArrayList<>(optionsRepository.findAll());
        List<Value> valueList = new ArrayList<>(valuesRepository.v(prod_id));

        model.addAttribute("reviews", reviewList);
        model.addAttribute("options", optionList);
        model.addAttribute("values", valueList);
        model.addAttribute("rating", reviewRepository.rate(prod_id));

        if (userService.getCurrentUser() != null){
            model.addAttribute("user", userRepository.r(userService.getCurrentUser().getId(), prod_id));
            model.addAttribute("ifadmin", userService.getCurrentUser().getAdmin());
        }else {
            model.addAttribute("ifadmin", false);
            model.addAttribute("user", "not");

        }

        return "data_ht4";
    }
}
