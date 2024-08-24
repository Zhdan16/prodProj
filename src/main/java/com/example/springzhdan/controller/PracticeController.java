package com.example.springzhdan.controller;

import com.example.springzhdan.pojo.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping(path = "/practice_controller")
public class PracticeController {
    Product[] products = new Product[]{
            new Product("Смартфоны", "Iphone", 500000),
            new Product("Ноутбуки", "ASUS", 700000),
            new Product("Телевизоры", "Samsung", 1000000),
            new Product("Ноутбуки", "lenovo", 400000)
    };
    Set<Product> products2 = new HashSet<>();


    @GetMapping(path="/practice_page_1")
    public Object getJSON(@RequestParam(name = "categoryName", required = false) String ctn){
        if (ctn != null){
            for (Product product : products) {
                if (product.getCategory().equals(ctn)) {
                    products2.add(product);
                }
            }
            return products2;
        }else {
            return products;
        }

    }

    @GetMapping(path="/practice_page_2")
    public Object getByPrise(@RequestParam(name = "from", required = false) Integer lprice, @RequestParam(name = "to", required = false) Integer hprice){
        List<Product> byPrice = new ArrayList<>();
        for (Product p: products) {
            if ((hprice != null && lprice != null) && (p.getPrice() <= hprice && p.getPrice() >= lprice)){
                byPrice.add(p);
            }else if ((hprice != null && lprice == null) && p.getPrice() <= hprice){
                byPrice.add(p);
            }else if ((hprice == null && lprice != null) && p.getPrice() >= lprice){
                byPrice.add(p);
            }
        }

        return byPrice;
    }


}
