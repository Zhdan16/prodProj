package com.example.springzhdan.service;

import com.example.springzhdan.enity.Orders;
import com.example.springzhdan.enity.User;

import com.example.springzhdan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User getCurrentUser() {
        return userRepository.getReferenceById(2L);
    }


    public String address(){
        return "123 Main St";
    }

}