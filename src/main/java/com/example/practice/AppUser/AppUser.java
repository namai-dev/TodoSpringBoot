package com.example.practice.AppUser;

import com.example.practice.roles.Roles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class AppUser {
    @Id
    @SequenceGenerator(name = "user_gen",sequenceName = "user_gen",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    Long id;
    String username;
    String email;
    String password;
    boolean isActive = true;
    String accessToken;
    String refreshToken;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "User_Roles",joinColumns = @JoinColumn(referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    public Collection<Roles>authorities = new ArrayList<>();
    public AppUser(String username, String email, String password,  Collection<Roles>rolesCollection) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = rolesCollection;

    }

}
