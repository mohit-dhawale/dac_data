package com.sunbeaminfo.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sunbeaminfo.dao.UserDaoImpl;
import com.sunbeaminfo.pojos.User;



/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			// create dao instance
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			throw new ServletException("init of " + getClass(), e);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("register.html");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		try{
				String firstName = req.getParameter("fn");
				String lastName = req.getParameter("ln");
				String email = req.getParameter("em");
				String password = req.getParameter("pass");
				LocalDate date  = LocalDate.parse(req.getParameter("db"));
				int age = Period.between(date, LocalDate.now()).getYears();
				if (age > 21) {
					User us = new User(firstName,lastName,email,password,Date.valueOf(date));
					us.setRole("voter");
					pw.print("<h5>"+ userDao.signup(us)+"</h5>");
				}else {
					pw.print("<h4> Invalid Age , </h4>");
				}
			}catch(Exception e) {
				pw.print("<h4>Error Exception </h4>");
				throw new ServletException("err in do-post : "+ getClass(),e); 
			}
		finally {
			if(pw !=null) {
				pw.close();
			}
		}
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		try {
			userDao.cleanUp();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
