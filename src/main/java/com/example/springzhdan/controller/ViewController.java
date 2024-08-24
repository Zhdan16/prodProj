package com.example.springzhdan.controller;

import com.example.springzhdan.pojo.Human;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/view_controller")
public class ViewController {
    @GetMapping(path = "/view_page_1")
    public String viewPage1(Model model){
        String message = "Message";
        model.addAttribute("message", message);
        return "view_page_1";
    }

    @GetMapping(path = "/view_page_2")
    public String viewPage2(Model model){
        Human human = new Human(3, "Maks");
        model.addAttribute("human", human);
        return "view_page_2";
    }

    @GetMapping(path = "/view_page_3")
    public String viewPage3(Model model){
        Human[] humans = new Human[]{
            new Human(34, "John"),
            new Human(15, "Mark"),
            new Human(28, "Alex"),
        };
        model.addAttribute("humans", humans);
        return "view_page_4";
    }
}
