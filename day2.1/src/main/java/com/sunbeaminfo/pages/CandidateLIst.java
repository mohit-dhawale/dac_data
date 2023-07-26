package com.sunbeaminfo.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminMainPage
 */
@WebServlet("/candidate_list")
public class CandidateLIst extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	//if instead of the ge get written as do Post then we will get an error called 405 method not implemented
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			// print a greeting to admin
			pw.print("<h4>In candidate list page </h4>");
			// retrieve clnt details from a cookie
			// 1. gett cookies from req.
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies)
					if (c.getName().equals("user_details")) {
						pw.print("<h5> User Details " + c.getValue() + "</h5>");
						break;
					}
			} else
				pw.print("<h4> Session Tracking Failed !!!!! , No Cookies !!! </h4>");

		}
	}

}
