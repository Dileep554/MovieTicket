package com.moviebookingapp.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moviebookingapp.Service.UserService;
import com.moviebookingapp.dto.ForgotPassword;
import com.moviebookingapp.exception.PasswordMismatchException;
import com.moviebookingapp.exception.UserAlredyExistException;
import com.moviebookingapp.exception.UserNotExistException;
//import com.moviebookingapp.kafka.Producer;
import com.moviebookingapp.models.User;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api/v1.0/moviebooking")
public class UserController {
	@Autowired
	private UserService userService;
	private static Logger log = LoggerFactory.getLogger(UserController.class);
//	@Autowired
//	Producer producer;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws UserAlredyExistException, PasswordMismatchException{
		
		log.info("register as new user");
		User newUser = userService.registerUser(user);
		//producer.sendMessage(newUser.getLoginId());
		log.info("New user registered");
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
		
		
	}
	
	@GetMapping("/login/{loginId}/{password}")
	public ResponseEntity<?> login(@PathVariable String loginId, @PathVariable String password){
		boolean result=userService.authenticate(loginId, password);
		log.info("logged in successfully");
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@PutMapping("/{loginId}/forgot")
	public ResponseEntity<String> forgotPassword(@PathVariable String loginId,@RequestBody ForgotPassword forgotPassword) throws PasswordMismatchException, UserNotExistException{
		userService.updatePassword(loginId, forgotPassword);
		//producer.sendMessage(loginId);
		log.info("password updated successfully");
		return new ResponseEntity<String>("Password updated successfully!!",HttpStatus.CREATED);
	}

}
