package com.example.springzhdan.controller;

import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import com.example.springzhdan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/products1")
public class GetProdAndDo {
    private final ProductRepository productRepository;
    private final ValuesRepository valuesRepository;
    private final OptionsRepository optionsRepository;
    private final ReviewRepository reviewRepository;
    public final UserService userService;
    public final UserRepository userRepository;
    public final BasketRepository basketRepository;

    @GetMapping(path = "")
    public String secondResource(Model model, @RequestParam(name = "filter", required = false) String category) {

        List<Product> listList = new ArrayList<>();
        if (category == null || category.isEmpty()) {
            listList.addAll(productRepository.findAll());
            listList.sort(Comparator.comparingLong(Product::getId));
            model.addAttribute("products", listList);

        } else {
            for (Product product : productRepository.findAll()) {
                if (product.getCategory().getName().equals(category)) {
                    listList.add(product);
                }
            }
            model.addAttribute("tex", category);
            model.addAttribute("products", listList);

        }
        return "data_ht";
    }

    @GetMapping(path = "/create")
    public String secondResource(Model model) {
        model.addAttribute("product", new Product());
        return "data_ht_2";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createProduct(Product product) {
        productRepository.save(product);
        return "redirect:/products1/create";
    }

    @GetMapping(path = "/remake")
    public String remakeProd(Model model, @RequestParam(name = "param") Long prod_id){
        Product product = productRepository.getReferenceById(prod_id);
        model.addAttribute("product", product);
        return "data_ht3";
    }


    @RequestMapping(value = "/remake", method = RequestMethod.POST)
    public String remakeProdOk(Product product) {

       productRepository.save(product);
        return "redirect:/products1";
    }

    @GetMapping(path = "/info")
    public String infoProd(Model model, @RequestParam(name = "prod_id") Long prod_id){

        Product product = productRepository.getReferenceById(prod_id);
        model.addAttribute("product", product);
        List<Review> reviewList = new ArrayList<>(reviewRepository.r(prod_id));
        List<Option> optionList = new ArrayList<>(optionsRepository.findAll());
        List<Value> valueList = new ArrayList<>(valuesRepository.findAll());

        model.addAttribute("reviews", reviewList);
        model.addAttribute("options", optionList);
        model.addAttribute("values", valueList);
        model.addAttribute("rating", reviewRepository.rate(prod_id));
        model.addAttribute("user", userRepository.r(userService.getCurrentUser().getId(), prod_id));
        model.addAttribute("basket", basketRepository.prods(userService.getCurrentUser().getId()).contains(product));
        return "data_ht4";
    }

    @GetMapping(path = "/rev")
    public String rev(Model model, @RequestParam(name = "tov_id") Long tov_id){
        Review review = new Review();
        Product product = productRepository.getReferenceById(tov_id);
        review.setProduct(product);
        review.setUser(userService.getCurrentUser());

        model.addAttribute("review", review);
        model.addAttribute("tov_id", tov_id);

        return "data_ht5";
    }

    @RequestMapping(value = "/rev", method = RequestMethod.POST)
    public String revOk(Review review) {
        review.setPublished(Boolean.TRUE);

        reviewRepository.save(review);

        return "redirect:/products1/info?prod_id=" + review.getProduct().getId();
    }

    @GetMapping(path = "/basket")
    public String basket(Model model){
        model.addAttribute("basket", basketRepository.prods(userService.getCurrentUser().getId()));
        return "data_ht6";
    }

    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public String basketAdd(@RequestParam(name = "tov_id") Long productId) {
        Product product = productRepository.getReferenceById(productId);
        Basket basket;
        if (basketRepository.bId(userService.getCurrentUser().getId(), productId) != null){
            basket = basketRepository.getReferenceById(basketRepository.bId(userService.getCurrentUser().getId(), productId));
            basket.setCount(basketRepository.count(userService.getCurrentUser().getId(), product.getId())+1);
        }else {
            basket = new Basket();
            basket.setCount(1);
        }
        basket.setUser(userRepository.getReferenceById(userService.getCurrentUser().getId()));
        basket.setProduct(product);
        basketRepository.save(basket);


        return "redirect:/products1/info?prod_id=" + productId;
    }
}





























