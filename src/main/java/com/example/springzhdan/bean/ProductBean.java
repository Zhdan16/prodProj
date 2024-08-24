package com.example.springzhdan.bean;

import com.example.springzhdan.pojo.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductBean {
    List<Product> products = new ArrayList<>();


    public Object lst(){
        return products;
    }

    public Object lst(String category){

        List<Product> products2 = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                products2.add(product);
            }
        }
        return products2;
    }

    public Object add(){
        return "Вы передали не все параметры для добавления товара в список";
    }

    public Object add(String category, String name, int price){
        products.add(new Product(category, name, price));
        return products;
    }
}
