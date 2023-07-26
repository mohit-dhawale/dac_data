package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlets
 * 
 * 
 */

@WebServlet("/test2")
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	System.out.println("in init2" + Thread.currentThread());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1. set reponse content type
		System.out.println("in do-get2" + Thread.currentThread());
		response.setContentType("text/html");
		//2. open text data stream
		try(PrintWriter pw = response.getWriter()){
			pw.print("<h4> Hello From Servelets, " + LocalDateTime.now() + "</h4>");
		}//Server Side JCM --pw flush() --> sending the response --> pw.close() => rendering the resp
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("in destroy" + Thread.currentThread());
	
	}
}
