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
public class RemakeProduct {
    public final CatalogService catalogService;
    @GetMapping(path = "/remake")
    public String remakeProd(Model model, @RequestParam(name = "param") Long prod_id, @RequestParam(name = "page") Integer page) {

        model.addAttribute("product", catalogService.productSearch(prod_id));
        model.addAttribute("page", page);
        return "data_ht3";
    }

    @RequestMapping(value = "/remake", method = RequestMethod.POST)
    public String remakeProdOk(Product product, Integer page) {

        catalogService.saveProd().save(product);
        return "redirect:/products?page=" + page;
    }
}
