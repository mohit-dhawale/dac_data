package com.sunbeaminfo.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeaminfo.dao.CandidateDaoImpl;
import com.sunbeaminfo.dao.UserDaoImpl;
import com.sunbeaminfo.pojos.Candidate;
import com.sunbeaminfo.pojos.User;

/**
 * Servlet implementation class AdminMainPage
 */
@WebServlet("/admin_main")
public class AdminMainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter())
		{
			HttpSession hs = request.getSession();
			User admin = (User)hs.getAttribute("user_dtls");
			System.out.println(admin);
			if(admin!=null) {
				pw.print("<h3>Welcome admin, "+ admin.getFirstName()+"</h3>");
			CandidateDaoImpl dao = (CandidateDaoImpl) hs.getAttribute("candidate_dao");
			List<Candidate> top2Candidates = dao.getTop2Candidates();
			pw.print("<h2>Top two Candidate</h2>");
			for(Candidate c1: top2Candidates){
				pw.print("<h4>"+c1.getName() + " "
						+ c1.getVotes()+"</h4>");
			}
			pw.print("<h2>PartyWise Votes</h2>");
			Map<String, Integer> partyvotes= dao.getPartywiseVotes();
			partyvotes.forEach((k,v)->{
				pw.printf("<h4>"+k +"   "+v+"</h4>");
			});
			}
			pw.print("<h3><a href='admin_ops.html'>Candidate Details</a></h3>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html"); n
		
	}

}
