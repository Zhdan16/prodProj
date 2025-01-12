package com.example.springzhdan.controller;
import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import com.example.springzhdan.service.CatalogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ProductsDelete {
    private final CatalogService catalogService;
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(@RequestParam(name = "prod_id") Long prodId) {
        catalogService.deleteProd(prodId);

        return "redirect:/products?page=1";
    }
}
