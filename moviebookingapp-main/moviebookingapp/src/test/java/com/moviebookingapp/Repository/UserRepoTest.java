package com.moviebookingapp.Repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.moviebookingapp.models.User;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserRepoTest {

	@Autowired
	UserRepo userRepo;
	
	User user;
	
	@BeforeEach
	void setUp() {
		user = new User();
		user.setLoginId("nehal123");
		user.setEmail("nehal@ghn.com");
		user.setFirstName("nehal");
		user.setLastName("ahmad");
		user.setPassword("123");
		user.setConfirmPassword("123");
		user.setContactNumber("9123456789");
		userRepo.save(user);
	}
	@AfterEach
	void tearDown() {
		userRepo.delete(user);
	}
	
	@Test
	void findByLoginIdTest() {
		System.out.println(user.getLoginId());
		User user1 = userRepo.findByloginId(user.getLoginId());
		System.out.println(user1);
		assertThat(user1.getClass()).isEqualTo(user.getClass());
	}

}
