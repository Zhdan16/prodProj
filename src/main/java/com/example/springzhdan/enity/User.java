package com.example.springzhdan.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Basket> baskets;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
}