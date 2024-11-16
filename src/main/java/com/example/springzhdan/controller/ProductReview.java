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
@RequestMapping(path = "")
public class ProductReview {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    public final UserService userService;
    @GetMapping(path = "/rev")
    public String rev(Model model, @RequestParam(name = "tov_id") Long tov_id) {
        Review review = new Review();
        Product product = productRepository.getReferenceById(tov_id);
        review.setProduct(product);
        review.setUser(userService.getCurrentUser());

        model.addAttribute("review", review);
        model.addAttribute("tov_id", tov_id);

        return "data_ht5";
    }

    @RequestMapping(value = "/rev", method = RequestMethod.POST)
    public String revOk(Review review) {
        review.setPublished(Boolean.FALSE);

        reviewRepository.save(review);

        return "redirect:/info?prod_id=" + review.getProduct().getId();
    }
}
