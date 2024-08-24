package com.example.springzhdan.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SomeBean {
    public int sum(int a, int b){
        return a + b;
    }
}
