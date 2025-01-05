package com.example.springzhdan.security;

import com.example.springzhdan.enity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private final User user;

    public UserDetailsImpl(User user){
        this.user = user;
    }

    @Override
    public String getUsername(){
        return user.getEmail();
    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "null";
        if (user.getAdmin().equals(true)){
            role = "ROLE_admin";
        }else if (user.getAdmin().equals(false)){
            role = "ROLE_user";
        }
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

}
