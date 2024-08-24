package com.example.springzhdan.controller;


import ch.qos.logback.core.model.Model;
import com.example.springzhdan.enity.Category;
import com.example.springzhdan.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/1")
public class DataController {
    private final CategoryRepository categoryRepository;

    @GetMapping(path = "/products")
    public Object secondResource(){
        return categoryRepository.findAll();
    }
}
