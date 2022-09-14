package com.example.practice.SecurityConfig;

import com.example.practice.AppUser.AppUser;
import com.example.practice.roles.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class MyAppUserDetails implements UserDetails {

    public String username;
    public String password;
    public Collection<SimpleGrantedAuthority> authorities;
    public boolean isActive;

    public AppUser appUser = new AppUser();

    public MyAppUserDetails(AppUser appUser) {
        this.username = appUser.getEmail();
        this.password = appUser.getPassword();
        this.isActive = appUser.isActive();
        authorities = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        AppUser appUser = new AppUser();
    Collection<GrantedAuthority>authorities1 = new ArrayList<>();
    appUser.getAuthorities().forEach(roles -> authorities1.add(new SimpleGrantedAuthority(roles.getRole())));
        return  authorities1;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
