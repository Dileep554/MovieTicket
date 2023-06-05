package com.moviebookingapp.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.moviebookingapp.Repository.UserRepo;
import com.moviebookingapp.dto.ForgotPassword;
import com.moviebookingapp.exception.PasswordMismatchException;
import com.moviebookingapp.exception.UserAlredyExistException;
import com.moviebookingapp.exception.UserNotExistException;
import com.moviebookingapp.models.User;
import static org.mockito.Mockito.when;

import java.util.Optional;

@SpringBootTest
//@RunWith(SpringRunner.class)
class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;
	
	@Mock
	private UserRepo userRepo;
	
	@Mock
	private User user;
	
//	@Test
//	public void contextLoads() throws Exception {
//		assertThat(userService).isNotNull();
//	}
	
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
	}
	
	@Test
	void registerUserTest() throws ConstraintViolationException, UserAlredyExistException, PasswordMismatchException{
		//user = new User("nehal123","nehal@ghn.com","nehal","ahmad","12345","12345","9123456789");
		
		when(userRepo.findByloginId("nehal123")).thenReturn(null);
		when(userRepo.save(user)).thenReturn(user);
		//System.out.println(user.getEmail());
		User user1 = userService.registerUser(user);
		System.out.println(user1.getEmail());
		assertEquals(user, user1 );
	}
	
	@Test
	void registerUserTestException() throws UserAlredyExistException{
		when(userRepo.findByloginId("nehal123")).thenReturn(user);
		assertThrows(UserAlredyExistException.class, ()-> {
			userService.registerUser(user);
		});
		
	}
	
	@Test
	void registerUserTestPasswordException() throws PasswordMismatchException{
		when(userRepo.findByloginId("nehal12")).thenReturn(null);
		assertThrows(PasswordMismatchException.class, ()-> {
			userService.registerUser(new User("nehal12","cef","cwf","fwf","rf","frwf","9123456789"));
		});
		
	}
	
	@Test
	void authenticateTest() {
		when(userRepo.findByloginId("nehal123")).thenReturn(user);
		assertEquals(true, userService.authenticate("nehal123", "123"));
		
	}
	
	@Test
	void updatePassword() throws PasswordMismatchException, UserNotExistException {
		when(userRepo.findByloginId("nehal123")).thenReturn(user);
		user.setPassword("12345");
		user.setConfirmPassword("12345");
		when(userRepo.save(user)).thenReturn(null);
		//assertEquals(null, userService.updatePassword("nehal123", new ForgotPassword("12345", "12345")));
		assertEquals("password changed successfully", userService.updatePassword("nehal123", new ForgotPassword("12345", "12345")));
	}
	
	@Test
	void updatePasswordException() throws UserNotExistException{
		when(userRepo.findByloginId("nehal123")).thenReturn(null);
		assertThrows(UserNotExistException.class, ()->
		userService.updatePassword("nehal123", new ForgotPassword()));
	}
	
	@Test
	void updatePasswordMismatchException() throws PasswordMismatchException{
		when(userRepo.findByloginId("nehal123")).thenReturn(user);
		assertThrows(PasswordMismatchException.class, ()->
		userService.updatePassword("nehal123", new ForgotPassword("123","12")));
	}
}
