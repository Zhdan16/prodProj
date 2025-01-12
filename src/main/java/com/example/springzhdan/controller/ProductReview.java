package com.example.springzhdan.controller;

import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import com.example.springzhdan.service.CatalogService;
import com.example.springzhdan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class ProductReview {
    public final CatalogService catalogService;
    public final UserService userService;
    @GetMapping(path = "/rev")
    public String rev(Model model, @RequestParam(name = "tov_id") Long tov_id) {
        Review review = new Review();

        review.setProduct(catalogService.productSearch(tov_id));
        review.setUser(userService.getCurrentUser());

        model.addAttribute("review", review);
        model.addAttribute("tov_id", tov_id);

        return "data_ht5";
    }

    @RequestMapping(value = "/rev", method = RequestMethod.POST)
    public String revOk(Review review) {
        review.setPublished(Boolean.FALSE);

        catalogService.saveRev().save(review);

        return "redirect:/info?prod_id=" + review.getProduct().getId();
    }
}
