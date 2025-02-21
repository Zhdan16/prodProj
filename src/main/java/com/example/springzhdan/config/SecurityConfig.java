package com.example.springzhdan.config;

import com.example.springzhdan.security.UserDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.authorizeHttpRequests(authConfigurer -> {
            authConfigurer.requestMatchers("/create", "/reviews", "/remake", "/del").hasRole("admin");
            authConfigurer.requestMatchers("/order", "/basket", "/rev").hasRole("user");
            authConfigurer.anyRequest().permitAll();
        });



        httpSecurity.formLogin(formLoginConfigurer -> {
            formLoginConfigurer.defaultSuccessUrl("/products");
        });

        httpSecurity.logout(logoutConfigurer -> {
            logoutConfigurer.logoutSuccessUrl("/products");
        });
        httpSecurity.formLogin(formLoginConfigurer -> {
            formLoginConfigurer
                    .loginPage("/login")
                    .defaultSuccessUrl("/products")
                    .permitAll();
        });
        
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
