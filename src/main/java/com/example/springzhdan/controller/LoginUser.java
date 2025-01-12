package com.example.springzhdan.controller;
import com.example.springzhdan.enity.*;
import com.example.springzhdan.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoginUser {
    @GetMapping(path = "/log")
    public String log(Model model) {
        return "redirect:/login";
    }
}