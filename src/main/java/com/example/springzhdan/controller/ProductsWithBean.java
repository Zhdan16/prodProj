package com.example.springzhdan.controller;

import com.example.springzhdan.bean.ProductBean;
import com.example.springzhdan.pojo.Human;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/products_task")
public class ProductsWithBean {
    private final ProductBean productBean;

    public ProductsWithBean(ProductBean productBean){
        this.productBean = productBean;
    }

    @GetMapping(path = "/list")
    public Object getList(@RequestParam(name = "categoryName", required = false) String category){
        if (category != null){
            return productBean.lst(category);
        }
        return productBean.lst();
    }

    @GetMapping(path = "/add")
    public Object addList(@RequestParam(name = "categoryName", required = false) String category, @RequestParam(name = "name", required = false) String name, @RequestParam(name = "price", required = false) Integer price){
        if (category != null && name != null && price != null){
            return productBean.add(category, name, price);
        }
        return productBean.add();
    }
}

