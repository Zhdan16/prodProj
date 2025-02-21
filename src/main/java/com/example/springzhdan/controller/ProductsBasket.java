package com.example.springzhdan.controller;

import com.example.springzhdan.enity.OrderProducts;
import com.example.springzhdan.repository.BasketRepository;
import com.example.springzhdan.service.CartService;
import com.example.springzhdan.service.CatalogService;
import com.example.springzhdan.service.OrderService;
import com.example.springzhdan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductsBasket {
    public final UserService userService;
    public final OrderService orderService;
    public final CartService cartService;
    public final CatalogService catalogService;

    @GetMapping(path = "/basket")
    public String basket(Model model, @RequestParam(name = "page", required = false) Integer page) {
        if (page == null) {
            return "redirect:/basket?page=1";
        }
        OrderProducts orderProducts = new OrderProducts();
        model.addAttribute("ord", orderProducts);
        model.addAttribute("basket", orderService.basketRepository.prods(userService.getCurrentUser().getId()));
        model.addAttribute("page", page);
        model.addAttribute("pages", (int) Math.ceil(orderService.basketRepository.prods(userService.getCurrentUser().getId()).size() / 6.0));
        return "data_ht6";
    }


    @RequestMapping(value = "/basket", method = RequestMethod.POST)
    public String basketAdd(@RequestParam(name = "tov_id") Long productId, @RequestParam(name = "if_bas", required = false) boolean ifBas, @RequestParam(name = "operation", required = false) String operation) {
        if (!ifBas){
            orderService.basketRepository.save(cartService.ifNotBas(productId));
            return "redirect:/info?prod_id=" + productId;
        }else {
            orderService.basketRepository.save(cartService.ifBas(productId, operation));
            return "redirect:/basket";
        }

    }
}
