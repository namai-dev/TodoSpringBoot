package com.example.practice.roles;

import com.example.practice.AppUser.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Roles {
    @Id
    @SequenceGenerator(name = "role_gen",sequenceName = "role_gen",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_gen")
    private Long id;
    private  String role;

    @ManyToMany
    private Collection<AppUser> appUserCollection;

    public Roles(String role) {
        this.role = role;
    }


}
