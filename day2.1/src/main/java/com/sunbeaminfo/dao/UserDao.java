package com.sunbeaminfo.dao;

import java.sql.SQLException;

import com.sunbeaminfo.pojos.User;

public interface UserDao {
//add a method for user authentication   
	User autheticateUser(String email, String password) throws SQLException;
	
	int signup(User us) throws SQLException;
}
