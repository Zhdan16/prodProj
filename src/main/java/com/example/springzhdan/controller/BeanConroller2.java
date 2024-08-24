package com.example.springzhdan.controller;


import com.example.springzhdan.bean.SomeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/second_bean_controller")
public class BeanConroller2 {

    private final SomeBean someBean;

    public BeanConroller2(SomeBean someBean){
        this.someBean = someBean;
    }

    @GetMapping(produces = "text/html")
    public Object page(){
        int r = someBean.sum(20, 15);
        String m = """
                Object: <b>%s</b><br/>
                Result: <b>%d</b>
                """;
        return m.formatted(someBean.toString(), r);
    }
}