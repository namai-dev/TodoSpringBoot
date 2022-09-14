package com.example.practice.AppUser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/amo/api/")
@RequiredArgsConstructor
public class UserController {
    public final AppUserService appUserService;

    @GetMapping(path = "/getUser")
    public List<AppUser> getAppUser(){
        return appUserService.getUsers();
    }

    @PostMapping(path = "/register")
    public String saveUser(@RequestBody AppUser appUser){
        appUserService.saveUser(appUser);
        return "Successful.......";
    }

    @GetMapping(path = "/appUser/{id}")
    public AppUser getSingleUser(@PathVariable("id") Long id){

            return appUserService.getSingleUse(id);

    }
}
