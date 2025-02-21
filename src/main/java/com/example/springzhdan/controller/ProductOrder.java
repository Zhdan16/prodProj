package com.example.springzhdan.controller;

import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import com.example.springzhdan.service.CatalogService;
import com.example.springzhdan.service.OrderService;
import com.example.springzhdan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductOrder {

    public final UserService userService;
    public final OrderService orderService;
    public final CatalogService catalogService;

    @GetMapping(path = "/order")
    public String order(Model model, @RequestParam(name = "page", required = false) Integer page) {
        model.addAttribute("order", orderService.prodByUserId());
        if (page == null) {
            return "redirect:/order?page=1";
        }
        model.addAttribute("basket", orderService.basketRepository.prods(userService.getCurrentUser().getId()));
        model.addAttribute("page", page);
        model.addAttribute("pages", (int) Math.ceil(orderService.prodByUserId().size() / 6.0));
        return "data_ht7";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String ord(@RequestParam(name = "tov_id") Long productId, @RequestParam(name = "count") Integer count) {
        orderService.orderRepository.save(orderService.placeANOrderUserInfo());
        orderService.orderProductsRepository.save(orderService.placeANOrderProductInfo(productId, count));
        orderService.basketRepository.delete(orderService.delProd(productId));
        return "redirect:/basket";
    }
}
