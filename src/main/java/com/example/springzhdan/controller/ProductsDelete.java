package com.example.springzhdan.controller;
import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "")
public class ProductsDelete {
    private final ProductRepository productRepository;
    private final ValuesRepository valuesRepository;
    private final ReviewRepository reviewRepository;
    public final BasketRepository basketRepository;
    public final OrderProductRepository orderProductsRepository;

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(@RequestParam(name = "prod_id") Long prodId) {
        Product product = productRepository.getReferenceById(prodId);

        List<Review> review = reviewRepository.findAll();
        for (Review r : review) {
            if (Objects.equals(r.getProduct().getId(), prodId)) {
                reviewRepository.delete(r);
            }
        }

        List<Value> values = valuesRepository.findAll();
        for (Value v : values) {
            if (Objects.equals(v.getProduct().getId(), prodId)) {
                valuesRepository.delete(v);
            }
        }

        List<Basket> baskets = basketRepository.findAll();
        for (Basket b : baskets) {
            if (Objects.equals(b.getProduct().getId(), prodId)) {
                basketRepository.delete(b);
            }
        }

        List<OrderProducts> orders = orderProductsRepository.findAll();
        for (OrderProducts o : orders) {
            if (Objects.equals(o.getProduct().getId(), prodId)) {
                orderProductsRepository.delete(o);
            }
        }

        productRepository.delete(product);

        return "redirect:/products";
    }
}
