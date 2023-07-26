package com.sunbeaminfo.beans;

import java.sql.SQLException;

import com.sunbeaminfo.dao.UserDaoImpl;
import com.sunbeaminfo.pojos.User;

public class UserBean {
	// client 's request parameters
	private String email;
	private String password;
	private String message;
	private String firstName;
	private String lastName;
	private String dob;
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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

	public void setMessage(String message) {
		this.message = message;
	}
	// dependancy : dao(tight coupling)
	private UserDaoImpl userDao;
	// user details : pojo
	private User userDetails;
	//constructor calling
	public UserBean() throws SQLException {
		userDao = new UserDaoImpl();
		System.out.println("user bean created !");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public String getMessage() {
		return message;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public User getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}

	// B.L method for authentication + authorization
	public String validateUser() throws SQLException {
		System.out.println("in validate user " + email + " " + password);
		// invoke dao's method
		userDetails = userDao.autheticateUser(email, password);
		if (userDetails == null) {
			// invalid login
			message = "Invalid Email or Password. plz try Again !!!!...";
			return "login";
		}
		// => valid login , role based authorization
		if (userDetails.getRole().equals("admin"))
			return "admin_main";
		// voter : role
		if (userDetails.isStatus())
			return "logout";
		return "candidate_list";

	}
	//ADD B.L validation and user registration
	public String registerUser(User voter) throws SQLException{
		String sts = userDao.registerNewVoter(voter);
		if (sts == null) {
			message = "Not Registered";
		}
		else {
			message = "User Registered Sucessfully";
		}
		return sts;
	}
}
