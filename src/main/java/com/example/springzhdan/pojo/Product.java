package com.example.springzhdan.pojo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Product {
    private final String category;

    private final String name;

    private final int price;
}
