package com.example.springzhdan.controller;
import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "")
public class RemakeProduct {
    private final ProductRepository productRepository;
    @GetMapping(path = "/remake")
    public String remakeProd(Model model, @RequestParam(name = "param") Long prod_id) {
        Product product = productRepository.getReferenceById(prod_id);
        model.addAttribute("product", product);
        return "data_ht3";
    }


    @RequestMapping(value = "/remake", method = RequestMethod.POST)
    public String remakeProdOk(Product product) {

        productRepository.save(product);
        return "redirect:/products";
    }


}
