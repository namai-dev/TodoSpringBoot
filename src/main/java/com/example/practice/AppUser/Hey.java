package com.example.practice.AppUser;

import com.example.practice.roles.RoleRepository;
import lombok.NoArgsConstructor;


public class Hey extends AppUserService{


    public Hey(UserRepository repository, RoleRepository roleRepository) {
        super(repository, roleRepository);
    }

    @Override
    public void saveToken(String username, String token, String refreshToken) {
        super.saveToken(username, token, refreshToken);
    }
}
