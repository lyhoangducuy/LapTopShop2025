package com.latptop.flexuy.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
            new SimpleGrantedAuthority("ROLE_" + user.getRole().getName())
        );
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // bạn không có trường này trong DB
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // bạn không có trường này trong DB
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // bạn không có trường này trong DB
    }

    @Override
    public boolean isEnabled() {
        return true; // bạn không có trường enabled trong DB
    }

    public User getUser() {
        return user;
    }
}
