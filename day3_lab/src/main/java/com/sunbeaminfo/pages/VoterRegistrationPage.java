package com.sunbeaminfo.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeaminfo.dao.UserDaoImpl;
import com.sunbeaminfo.pojos.User;

/**
 * Servlet implementation class VoterRegistrationPage
 */
@WebServlet("/voter_register")
public class VoterRegistrationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	public void init() throws ServletException {
		try {
			// create dao instance
			userDao = new UserDaoImpl();

		} catch (Exception e) {
			// centralized exc handling in servlet
			// How to inform WC , that init has failed?
			// Ans : by throwing ServletExc to the WC
			// ServletException(String mesg , Throwable rootCause)
			throw new ServletException("init of " + getClass(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			userDao.cleanUp();

		} catch (Exception e) {
			System.out.println("err in destroy " + getClass());
			throw new RuntimeException("err in destroy " + getClass(), e);// not necessary !
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. set resp cont type
		response.setContentType("text/html");
		// 2. PW
		PrintWriter pw = response.getWriter();
		try {

			// 3. read req params :
			String firstName = request.getParameter("fn");
			String lastName = request.getParameter("ln");
			String email = request.getParameter("em");
			String pwd = request.getParameter("pass");
			LocalDate date = LocalDate.parse(request.getParameter("dob"));
			int age = Period.between(date, LocalDate.now()).getYears();
			if (age > 21) {
				// validation success , create voter instance
				User user = new User(firstName, lastName, email, pwd, Date.valueOf(date));
				// voting status : false by default
				user.setRole("voter");
				// invoke dao's method
				pw.print("<h5>" + userDao.registerNewVoter(user) + " </h5>");
			} else
				pw.print("<h5> Invalid Age , Voter Registration Failed !!!</h5>");
		} catch (Exception e) {
			pw.print("<h5> Voter Reg Failed " + e.getMessage() + "</h5>");
			throw new ServletException("err in do-post :" + getClass(), e);
		} finally {
			if (pw != null)
				pw.close();
		}

	}

}
