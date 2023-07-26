package com.sunbeaminfo.beans;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

import com.sunbeaminfo.dao.UserDaoImpl;
import com.sunbeaminfo.pojos.User;

public class UserBean {
	// clnt 's req params
	private String email;
	private String password;
	// additional props for voter (user) registration
	private String firstName;
	private String lastName;
	private String dob;

	// dependency : dao (tight coupling)
	private UserDaoImpl userDao;
	// user details : pojo
	private User userDetails;
	// add status message property
	private String message;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	// B.L method for authentication + authorization
	public String validateUser() throws SQLException {
		System.out.println("in validate user " + email + " " + password);
		// invoke dao's method
		userDetails = userDao.autheticateUser(email, password);
		if (userDetails == null) {
			// invalid login
			message = "Invalid Email or password , pls retry !!!";
			return "login";
		}
		message = "Welcome " + userDetails.getRole();
		// => valid login , role based authorization
		if (userDetails.getRole().equals("admin"))
			return "admin_main";
		// voter : role
		if (userDetails.isStatus())
			return "logout";
		return "candidate_list";

	}

	// add B.L method for validation n voter registration
	public String registerNewVoter() {
		System.out.println("in voter reg " + firstName + " " + dob);
		// parse string(dob) -->LocalDate
		LocalDate date = LocalDate.parse(dob);
		int age = Period.between(date, LocalDate.now()).getYears();
		if (age > 21) {
			// create User(voter) instance

			User voter = new User(firstName, lastName, email, password, Date.valueOf(date));
			// valid age , invoke dao's method
			try {
				return userDao.registerNewVoter(voter);
			} catch (Exception e) {
				return "User registraiton failed ,"+e.getMessage();
			}
		}
		return "User registraiton failed , Invalid Age !";
	}
}
