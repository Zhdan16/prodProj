package com.example.springzhdan.controller;

import com.example.springzhdan.enity.Product;
import com.example.springzhdan.enity.Review;
import com.example.springzhdan.repository.ReviewRepository;
import com.example.springzhdan.service.CatalogService;
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
public class ReviewsCheck {
    private final CatalogService catalogService;

    @GetMapping(path = "/reviews")
    public String remakeProd(Model model) {

        model.addAttribute("check", catalogService.checking(false));
        return "data_ht8";
    }

    @RequestMapping(path = "/reviews", method = RequestMethod.POST)
    public String remProd(@RequestParam(name = "comId") Long id){

        Review review = catalogService.reviewSearch(id);
        review.setPublished(Boolean.TRUE);
        catalogService.saveRev().save(review);

        return "redirect:/reviews";
    }
}
