package com.moviebookingapp.Service;

import javax.validation.ConstraintViolationException;

import com.moviebookingapp.dto.ForgotPassword;
import com.moviebookingapp.exception.PasswordMismatchException;
import com.moviebookingapp.exception.UserAlredyExistException;
import com.moviebookingapp.exception.UserNotExistException;
import com.moviebookingapp.models.User;

public interface UserService {

	public User registerUser(User user) throws ConstraintViolationException, PasswordMismatchException, UserAlredyExistException;
	public boolean authenticate(String loginId, String password);
	public String updatePassword(String loginId, ForgotPassword forgotPassword) throws PasswordMismatchException, UserNotExistException;
}
