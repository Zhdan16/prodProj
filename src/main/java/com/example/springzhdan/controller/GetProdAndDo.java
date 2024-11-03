package com.example.springzhdan.controller;

import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import com.example.springzhdan.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Controller;
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
    public final OrderProductRepository orderProductsRepository;
    public  final OrderRepository orderRepository;


    @GetMapping(path = "")
    public String secondResource(Model model, @RequestParam(name = "filter", required = false) String category) {

        List<Product> listList = new ArrayList<>();
        if (category == null || category.isEmpty()) {
            listList.addAll(productRepository.findAll());
            listList.sort(Comparator.comparingLong(Product::getId));
            model.addAttribute("products", listList);

        } else {
            for (Product product : productRepository.findAll()) {
                if (product.getCategory().getName().toLowerCase().contains(category.toLowerCase())) {
                    listList.add(product);
                }
            }

            model.addAttribute("tex", category);
            model.addAttribute("products", listList);

        }
        model.addAttribute("admin", userService.getCurrentUser());
        model.addAttribute("ifadmin", userService.getCurrentUser().getAdmin());
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + userService.getCurrentUser().getAdmin() + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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
        System.out.println("product: " + product.getId());

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
        OrderProducts orderProducts = new OrderProducts();
        model.addAttribute("ord", orderProducts);
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

    @GetMapping(path = "/order")
    public String order(Model model){
        model.addAttribute("order", orderProductsRepository.order(userService.getCurrentUser().getId()));
        return "data_ht7";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String ord(@RequestParam(name = "tov_id") Long productId, @RequestParam(name = "count") Integer count) {
        Orders order = new Orders();
        OrderProducts orderProducts = new OrderProducts();
        java.util.Date date = new java.util.Date();
        order.setUser(userService.getCurrentUser());
        order.setAddress(userService.address());
        order.setOrderDate(date);
        order.setStatus(1);
        orderRepository.save(order);

        orderProducts.setOrder(order);
        orderProducts.setProduct(productRepository.getReferenceById(productId));
        orderProducts.setCount(count);
        orderProductsRepository.save(orderProducts);
        basketRepository.delete(basketRepository.getReferenceById(basketRepository.bId(userService.getCurrentUser().getId(), productId)));

        return "redirect:/products1/basket";
    }
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(@RequestParam(name = "prod_id") Long prodId){
        System.out.println("12837108273012038128");
        Product product = productRepository.getReferenceById(prodId);

        List<Review> review = reviewRepository.findAll();
        for (Review r : review){
            if(Objects.equals(r.getProduct().getId(), prodId)){
                reviewRepository.delete(r);
                System.out.println("ok");
            }
        }

        List<Value> values = valuesRepository.findAll();
        for (Value v : values){
            if(Objects.equals(v.getProduct().getId(), prodId)){
                valuesRepository.delete(v);
                System.out.println("okgg");
            }
        }

        List<Basket> baskets = basketRepository.findAll();
        for (Basket b : baskets){
            if(Objects.equals(b.getProduct().getId(), prodId)){
                basketRepository.delete(b);
                System.out.println("oksdfsdf");
            }
        }

        List<OrderProducts> orders = orderProductsRepository.findAll();
        for (OrderProducts o : orders){
            if(Objects.equals(o.getProduct().getId(), prodId)){
                orderProductsRepository.delete(o);
                System.out.println("y");
            }
        }


        productRepository.delete(product);

        return "redirect:/products1";
    }
}





























