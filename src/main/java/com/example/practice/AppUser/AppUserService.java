package com.example.practice.AppUser;

import com.example.practice.roles.RoleRepository;
import com.example.practice.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {
    @Autowired
    public final  UserRepository repository;
    @Autowired
    public final  RoleRepository roleRepository;

    @Autowired
   PasswordEncoder passwordEncoder;



    public AppUser saveUser(AppUser appUser){
        Optional<AppUser> appUser1 = repository.findByUsername(appUser.getUsername());
        if(appUser1.isPresent()){
            throw new IllegalStateException("User with the provided email is present");
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return repository.save(appUser);
    }

    public List<AppUser> getUsers(){

        return repository.findAll();
    }

    public AppUser getSingleUse(Long id) throws UsernameNotFoundException{

        return repository.findById(id).orElseThrow(()->

             new UsernameNotFoundException("User Not Found")
        );
    }

    public void addRoleToUser(String email, String roleName){
        AppUser appUser = repository.findByEmail(email);
        Roles roles = roleRepository.findByRole(roleName).get();
        System.out.println(roles.getRole());
        if(appUser != null && roles != null){
            appUser.getAuthorities().add(roles);
            appUser.getAuthorities().forEach(roles1 -> System.out.println(roles1.getRole()));
        }
        else {

            throw new UsernameNotFoundException("User not found");
        }

    }

    @PostConstruct
    public void myThing(){
        AppUser appUser = new AppUser();
        appUser.getAuthorities().forEach(roles -> roles.getRole());
    }

    public  void saveToken(String username, String token, String refreshToken){
        Optional<AppUser> appUser = repository.findByUsername(username);
        appUser.get().setRefreshToken(refreshToken);
        appUser.get().setAccessToken(token);
    }


}
