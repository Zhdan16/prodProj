package com.example.springzhdan.controller;
import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import com.example.springzhdan.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CreateProduct {
    public final CatalogService catalogService;
    @GetMapping(path = "/create")
    public String secondResource(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("catChoose", catalogService.categoryElem());
        return "data_ht2";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProduct(Product product) {
        catalogService.saveProd().save(product);
        return "redirect:/create";
    }
}
