package com.example.springzhdan.controller;

import com.example.springzhdan.enity.Option;
import com.example.springzhdan.enity.Product;
import com.example.springzhdan.enity.Review;
import com.example.springzhdan.enity.Value;
import com.example.springzhdan.repository.*;
import com.example.springzhdan.service.CatalogService;
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
public class InfoProductPage {
    public final CatalogService catalogService;
    public final UserService userService;

    @GetMapping(path = "/info")
    public String infoProd(Model model, @RequestParam(name = "prod_id") Long prod_id) {
        model.addAttribute("product", catalogService.productSearch(prod_id));
        model.addAttribute("reviews", catalogService.reviewList(prod_id));
        model.addAttribute("options", catalogService.optionList());
        model.addAttribute("values", catalogService.valueList(prod_id));
        model.addAttribute("rating", String.format("%.1f", catalogService.rating(prod_id)));

        if (userService.getCurrentUser() != null){
            model.addAttribute("user", catalogService.rev(prod_id));
            model.addAttribute("ifadmin", userService.getCurrentUser().getAdmin());
        }else {
            model.addAttribute("ifadmin", false);
            model.addAttribute("user", "not");

        }

        return "data_ht4";
    }
}
