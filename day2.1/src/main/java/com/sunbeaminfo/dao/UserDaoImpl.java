package com.sunbeaminfo.dao;

import java.sql.*;

import com.sunbeaminfo.pojos.User;
//import ALL static members of DBUtils class
import static com.sunbeaminfo.utils.DBUtils.*;

public class UserDaoImpl implements UserDao {
	// fields
	private Connection connection;
	private PreparedStatement pst1;
	private PreparedStatement pst2;

	public UserDaoImpl() throws SQLException {
		// open cn
		connection = openConnection();
		// pst1
		pst1 = connection.prepareStatement("select * from users where email=? and password=?");
		pst2 = connection.prepareStatement("insert into users(first_name,last_name,email,password,dob,status,role) values (?,?,?,?,?,?,?)");
		System.out.println("user dao created !");
	}

	@Override
	public User autheticateUser(String email, String password) throws SQLException {
		// 1. set IN params
		pst1.setString(1, email);
		pst1.setString(2, password);
		try (ResultSet rst = pst1.executeQuery()) {
			/*
			 * int userId, String firstName, String lastName, String email, String password,
			 * Date dob, boolean status, String role
			 */
			if (rst.next())
				return new User(rst.getInt(1), 
						rst.getString(2), rst.getString(3), 
						email, password, rst.getDate(6),
						rst.getBoolean(7), rst.getString(8));
		}
		return null;
	}
	
	@Override
	public int signup(User us) throws SQLException{
		// TODO Auto-generated method stub
		int cnt =0;
		pst2.setString(1, us.getFirstName());
		pst2.setString(2, us.getLastName());
		pst2.setString(3, us.getEmail());
		pst2.setString(4, us.getPassword());
		pst2.setDate(5, us.getDob());
		pst2.setBoolean(6, us.isStatus());
		pst2.setString(7, us.getRole());
		cnt = pst2.executeUpdate();
		
		return cnt;
		
	}

	// clean up
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		closeConnection();
		System.out.println("user dao cleaned up !");

	}

}
