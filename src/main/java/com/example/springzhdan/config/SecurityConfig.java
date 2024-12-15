package com.example.springzhdan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.authorizeHttpRequests(authConfigurer -> {
            authConfigurer.requestMatchers("/products").authenticated();
            authConfigurer.anyRequest().permitAll();
        });

        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
