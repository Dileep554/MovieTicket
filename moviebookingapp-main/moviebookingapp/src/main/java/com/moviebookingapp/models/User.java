package com.moviebookingapp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document("Users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@NotNull(message="LoginId is required")
	@Indexed
	private String loginId;
	
	@NotNull(message="Email is required")
	@Email
	@Size(min=2,message="Email is required")
	@Indexed(unique=true)
	private String email;
	
	public String getLoginId() {
		return loginId;
	}

	public User() {
		super();
	}

	public User(@NotNull(message = "LoginId is required") String loginId,
			@NotNull(message = "Email is required") @Email @Size(min = 2, message = "Email is required") String email,
			@NotNull(message = "First Name is required") @Size(min = 2, message = "First Name is required") String firstName,
			@NotNull(message = "Last Name is required") @Size(min = 1, message = "Last Name is required") String lastName,
			@NotNull(message = "Password is required") @Size(min = 2, message = "Password is required") String password,
			@NotNull(message = "Confirm Password is required") @Size(min = 2, message = "Confirm Password is required") String confirmPassword,
			@NotNull(message = "Contact Number is required") @Size(min = 10, max = 10, message = "Contact Number is required") String contactNumber) {
		super();
		this.loginId = loginId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.contactNumber = contactNumber;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@NotNull(message="First Name is required")
	@Size(min=2,message="First Name is required")
	private String firstName;
	
	@NotNull(message="Last Name is required")
	@Size(min=1, message="Last Name is required")
	private String lastName;
	
	@NotNull(message="Password is required")
	@Size(min=2, message="Password is required")
	private String password;
	
	@NotNull(message="Confirm Password is required")
	@Size(min=2, message="Confirm Password is required")
	private String confirmPassword;
	
	@NotNull(message="Contact Number is required")
	@Size(min=10,max=10,message="Contact Number is required")
	private String contactNumber;
	
}
