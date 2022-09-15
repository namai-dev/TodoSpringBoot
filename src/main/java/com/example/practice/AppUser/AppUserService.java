package com.example.practice.AppUser;

import com.example.practice.roles.RoleRepository;
import com.example.practice.roles.Roles;
import com.example.practice.todo.Todo;
import com.example.practice.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {
    @Autowired
    public final  UserRepository repository;
    @Autowired
    public final  RoleRepository roleRepository;

    public final TodoRepository todoRepository;


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

    public void deleteUser(Long id){
        repository.deleteById(id);
    }

    public Collection<Todo> getTodos(String username){
        Optional<AppUser>appUser = repository.findByUsername(username);
        if(!appUser.isPresent()){

            throw  new UsernameNotFoundException("username provide not available");
        }
        else {
            return appUser.get().getTodos();
        }
    }

    public void deleteTodo(String username, Long id){
        Optional<AppUser>appUser = repository.findByUsername(username);
        if(appUser.isEmpty()){
            throw  new UsernameNotFoundException("Username provided not available");
        }
        else {
            todoRepository.deleteById(id);

        }
    }

    public void userTodo(String username, Todo todo){
        Optional<AppUser>appUser = repository.findByUsername(username);

        if(appUser.get() == null){
            throw new UsernameNotFoundException("User not Found");
        }
        else{
            appUser.get().getTodos().add(todo);
            todoRepository.save(todo);
        }

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
