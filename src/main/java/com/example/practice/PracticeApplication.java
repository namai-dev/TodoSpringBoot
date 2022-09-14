package com.example.practice;

import com.example.practice.AppUser.AppUser;
import com.example.practice.AppUser.AppUserService;
import com.example.practice.AppUser.UserRepository;
import com.example.practice.roles.RoleService;
import com.example.practice.roles.Roles;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Data
@SpringBootApplication
public class PracticeApplication {
	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(@Autowired AppUserService service, @Autowired RoleService roleService){
		return args -> {

			service.saveUser(new AppUser("nams","nams@gmail.com","nams444",new ArrayList<>()));
			service.saveUser(new AppUser("kings","kings@gmail.com","nams444", new ArrayList<>()));
			service.saveUser(new AppUser("james","james@gmail.com","nams444", new ArrayList<>()));
			service.saveUser(new AppUser("kilos","kilos@gmail.com","nams444", new ArrayList<>()));

			roleService.saveRele(new Roles("Admin"));
			roleService.saveRele(new Roles("User"));
			roleService.saveRele(new Roles("Manager"));
			service.addRoleToUser("nams@gmail.com","Admin");
			service.addRoleToUser("nams@gmail.com", "Manager");


		};
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(10);
	}

}
