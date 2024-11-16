package com.example.springzhdan.controller;

import com.example.springzhdan.enity.Product;
import com.example.springzhdan.enity.Review;
import com.example.springzhdan.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "")
public class ReviewsCheck {
    private final ReviewRepository reviewRepository;

    @GetMapping(path = "/reviews")
    public String remakeProd(Model model) {

        model.addAttribute("check",reviewRepository.check(false));
        return "data_ht8";
    }

    @RequestMapping(path = "/reviews", method = RequestMethod.POST)
    public String remProd(@RequestParam(name = "comId") Long id){

        Review review = reviewRepository.getReferenceById(id);
        review.setPublished(Boolean.TRUE);
        reviewRepository.save(review);

        return "redirect:/reviews";
    }
}
