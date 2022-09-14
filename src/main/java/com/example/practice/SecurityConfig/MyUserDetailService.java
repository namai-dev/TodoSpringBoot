package com.example.practice.SecurityConfig;

import com.example.practice.AppUser.AppUser;
import com.example.practice.AppUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    public UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser>appUser = userRepository
                .findByUsername(username);
                if(!appUser.isPresent()){
                    throw new UsernameNotFoundException("Not Found");
                }
        Collection<SimpleGrantedAuthority>authorities = new ArrayList<>();
                appUser.get().getAuthorities().forEach(roles -> authorities.add(new SimpleGrantedAuthority(roles.getRole())));
//        return appUser.map(MyAppUserDetails::new).get();
        return new User(appUser.get().getUsername(),appUser.get().getPassword(), authorities);
    }
}
