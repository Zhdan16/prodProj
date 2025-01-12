package com.example.springzhdan.service;

import com.example.springzhdan.enity.User;
import com.example.springzhdan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public User getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = authentication.getName();

        return userRepository.user(username);
    }

    public String address(){
        return "123 Main St";
    }

}