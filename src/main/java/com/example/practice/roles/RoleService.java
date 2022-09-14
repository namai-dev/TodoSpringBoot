package com.example.practice.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    public final RoleRepository repository;
    public Roles saveRele(Roles roles){
        return repository.save(roles);

    }

}
