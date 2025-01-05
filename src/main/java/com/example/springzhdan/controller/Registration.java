package com.example.springzhdan.controller;

import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import com.example.springzhdan.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping(path = "")
public class Registration {

    public final UserRepository userRepository;
    @GetMapping(path = "/registration")
    public String reg(Model model, @RequestParam(name = "tov_id") Long tov_id) {
        User user = new User();
        model.addAttribute("reg", user);
        model.addAttribute("tov_id", tov_id);

        return "data_ht9";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String regOk(User user) {
        user.setAdmin(false);
        userRepository.save(user);

        return "redirect:/products" ;
    }
}
