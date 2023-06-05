package com.moviebookingapp.serviceImpl;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebookingapp.Repository.UserRepo;
import com.moviebookingapp.Service.UserService;
import com.moviebookingapp.controller.MovieController;
import com.moviebookingapp.dto.ForgotPassword;
import com.moviebookingapp.exception.PasswordMismatchException;
import com.moviebookingapp.exception.UserAlredyExistException;
import com.moviebookingapp.exception.UserNotExistException;
import com.moviebookingapp.models.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
	private static Logger log = LoggerFactory.getLogger(MovieController.class);
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User registerUser(User user) throws ConstraintViolationException, UserAlredyExistException, PasswordMismatchException{
		log.info("finding user");
		if(userRepo.findByloginId(user.getLoginId())==null) {
			if(user.getPassword().equals(user.getConfirmPassword())) {
				log.info("registring user");
				User user1 =userRepo.save(user);
				return user1;
		}
			else {
				log.error("rules are not fullfilled");
				throw new PasswordMismatchException("Password is not matching");
			}
		}	
		else {
			log.error("user is already registered");
			throw new UserAlredyExistException("LoginId Already exist");
		}
		
	}

	@Override
	public boolean authenticate(String loginId, String password) {
		log.info("finding user");
		User user = userRepo.findByloginId(loginId);
		if(user==null) {
			log.error("user not found");
			return false;
		}
		if(user.getPassword().equals(password)) {
			log.info("user logged in");
			return true;
		}
		else
			return false;
	}

	@Override
	public String updatePassword(String loginId, ForgotPassword forgotPassword) throws PasswordMismatchException, UserNotExistException {
		log.info("finding user");
		User user = userRepo.findByloginId(loginId);
		if(user==null) {
			log.error("user not found");
			throw new UserNotExistException("This login id doesnot exist");
		}
		else {
		if(forgotPassword.getPassword().equals(forgotPassword.getConfirmPassword()))
		{
			log.info("updating password");
			user.setPassword(forgotPassword.getPassword());
			user.setConfirmPassword(forgotPassword.getConfirmPassword());
			userRepo.save(user);
			log.info("password updated successfully");
			return "password changed successfully";
		}
		else {
			log.error("rules are not fullfilled");
			throw new PasswordMismatchException("Password is not matching");
		}
		}
	}

}
