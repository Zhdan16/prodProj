package com.example.springzhdan.controller;

import com.example.springzhdan.pojo.TestProd;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "")
public class TestPage {

    @GetMapping(path = "/shop")
    public String TestPage2(Model model, @RequestParam(name = "page", required = false) Integer page){
        TestProd[] testProds = new TestProd[]{
            new TestProd("a", 10),
            new TestProd("b", 20),
            new TestProd("c", 30),
            new TestProd("d", 40),
            new TestProd("e", 50),
            new TestProd("f", 60),
            new TestProd("g", 70),
            new TestProd("h", 80),
            new TestProd("i", 90),
            new TestProd("j", 100),
            new TestProd("k", 110),
            new TestProd("l", 120)
        };

        List<TestProd> testProdList = new ArrayList<>();
        if(page == null){
            for (int y = 0; y < 2; y++){
                testProdList.add(testProds[y]);
            }
        }else {
            for (int t = page-1; t < page+1; t++){
                testProdList.add(testProds[t]);
            }

        }
        model.addAttribute("products", testProdList);
        List<Integer> but = new ArrayList<>();
        for (int t = 1; t < testProds.length / 2; t++){
            but.add(t);
        }
        model.addAttribute("but", but);
        return "test_page";
    }
}
