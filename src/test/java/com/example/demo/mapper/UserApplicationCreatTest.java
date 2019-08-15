package com.example.demo.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mapper.UserMapper;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationCreatTest {
	@Autowired
	private UserMapper mapper;
	

	@Test
	public void contextLoads() {
//		User user=new User();
//		user.setUsername("daima");
//		user.setPassword("daima");
//		user.setRealName("libai");
//		Integer row=mapper.insert(user);
	}
	@Test
	public void findAllUser() {
//		String username="daima";
//		String password= "daima";
//		User user=mapper.findAllUser(username, password);
//		System.err.println("user:"+user);
	}


}
