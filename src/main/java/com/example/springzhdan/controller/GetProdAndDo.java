package com.example.springzhdan.controller;

import com.example.springzhdan.enity.Option;
import com.example.springzhdan.enity.Product;
import com.example.springzhdan.enity.Review;
import com.example.springzhdan.enity.Value;
import com.example.springzhdan.repository.OptionsRepository;
import com.example.springzhdan.repository.ProductRepository;
import com.example.springzhdan.repository.ReviewRepository;
import com.example.springzhdan.repository.ValuesRepository;
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
    public String remakeProd(Model model, @RequestParam(name = "param") Integer prod_id){
        List<Product> listList = new ArrayList<>(productRepository.findAll());
        for (Product product: listList){
            if (product.getId().intValue() == prod_id){
                model.addAttribute("product", product);
                break;
            }
        }

        return "data_ht3";
    }


    @RequestMapping(value = "/remake", method = RequestMethod.POST)
    public String remakeProdOk(Product product) {

       productRepository.save(product);
        return "redirect:/products1";
    }

    @GetMapping(path = "/info")
    public String infoProd(Model model, @RequestParam(name = "prod_id") Integer prod_id){
        List<Product> listList = new ArrayList<>(productRepository.findAll());
        for (Product product: listList){
            if (product.getId().intValue() == prod_id){
                model.addAttribute("product", product);
                break;
            }
        }
        List<Review> reviewList = new ArrayList<>(reviewRepository.findAll());
        List<Option> optionList = new ArrayList<>(optionsRepository.findAll());
        List<Value> valueList = new ArrayList<>(valuesRepository.findAll());

        int sum = 0;
        int sr = 0;
        for (Review r : reviewList){
            if (r.getPublished() && r.getProduct().getId().intValue() == prod_id){
                sum++;
                sr += r.getRate();
            }
        }

        int rating = Math.round((float) sr/sum);
        model.addAttribute("reviews", reviewList);
        model.addAttribute("options", optionList);
        model.addAttribute("values", valueList);
        model.addAttribute("rating", rating);

        return "data_ht4";
    }

    @GetMapping(path = "/rev")
    public String rev(Model model, @RequestParam(name = "tov_id") Integer tov_id){

        Review review = new Review();
        List<Product> listList = new ArrayList<>(productRepository.findAll());
        for (Product product: listList){
            if (product.getId().intValue() == tov_id){
                review.setProduct(product);
                break;
            }
        }
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
}





























