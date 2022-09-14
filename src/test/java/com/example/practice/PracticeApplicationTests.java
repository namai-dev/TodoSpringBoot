package com.example.practice;

import com.example.practice.AppUser.AppUser;
import com.example.practice.AppUser.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class PracticeApplicationTests {
	@Autowired
	AppUserService appUserService;

	@Test
	void contextLoads() {
	}
	@Test
	public void testing(){
		appUserService.saveUser(new AppUser("namsi","namsi@gmail.com","Jomatech",new ArrayList<>()));
	}
	@Test
	public void test2(){
		appUserService.getSingleUse(3L);
	}



}
