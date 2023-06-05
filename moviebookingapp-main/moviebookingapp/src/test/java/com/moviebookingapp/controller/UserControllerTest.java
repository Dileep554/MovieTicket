package com.moviebookingapp.controller;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.when;

import com.moviebookingapp.Service.UserService;
import com.moviebookingapp.dto.ForgotPassword;
import com.moviebookingapp.exception.PasswordMismatchException;
import com.moviebookingapp.exception.UserAlredyExistException;
import com.moviebookingapp.exception.UserNotExistException;
import com.moviebookingapp.models.User;

@SpringBootTest
class UserControllerTest {

	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;
	@Mock
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
	}
	
	@Test
	void registerUserTest() throws PasswordMismatchException, UserAlredyExistException {
		Mockito.when(userService.registerUser(user)).thenReturn(user);
		assertEquals(new ResponseEntity<User>(user, HttpStatus.CREATED), userController.registerUser(user));
	}

	@Test
	void loginTest() {
		when(userService.authenticate(user.getLoginId(),user.getPassword())).thenReturn(true);
		assertEquals(new ResponseEntity<>(true,HttpStatus.OK), userController.login("nehal123", "123"));
	}
	
	
	@Test
	void forgotPasswordTest() throws PasswordMismatchException, UserNotExistException {
		ForgotPassword newPassword = new ForgotPassword("12345","12345");
		when(userService.updatePassword(user.getLoginId(),newPassword)).thenReturn(null);
		assertEquals(new ResponseEntity<String>("Password updated successfully!!",HttpStatus.CREATED), 
				userController.forgotPassword("nehal123", new ForgotPassword("12345","12345")));
		
	}
}
