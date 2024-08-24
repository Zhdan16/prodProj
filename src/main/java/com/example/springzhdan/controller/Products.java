package com.example.springzhdan.controller;


import com.example.springzhdan.pojo.Human;
import com.example.springzhdan.pojo.ProductsList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "")
public class Products{

    @GetMapping(path = "/products")
    public String Products2(Model model, @RequestParam(name = "filter", required = false) String category){

        ProductsList[] productsList = new ProductsList[]{
                new ProductsList("a", "p1", 200, true),
                new ProductsList("a", "p2", 150, false),
                new ProductsList("b", "p3", 200, true),
                new ProductsList("a", "p4", 300, true),
                new ProductsList("b", "p5", 200, false),
        };

        List<ProductsList> listList = new ArrayList<>();
        if(category == null || category.isEmpty()){
            for (ProductsList productsList1: productsList){
                if(productsList1.isAvailable()){
                    listList.add(productsList1);
                }
            }
            model.addAttribute("products", listList);

        }else {

            for (ProductsList product : productsList){
                if(product.getCategory().equals(category) && product.isAvailable()){
                    listList.add(product);
                }
            }
            model.addAttribute("tex", category);
            model.addAttribute("products", listList);
        }

        return "view_page_4";
    }
}