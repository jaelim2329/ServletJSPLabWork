package com.mantiso;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/home", "*.do"})
public class SimpleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "password";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		
		if (name != null){
			resp.getWriter().printf("Name: %s\n", name);
		} 
		else {
			resp.getWriter().write("Please enter a name.");
		}
		
		resp.getWriter().write(LocalDateTime.now().toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Set response content type
		resp.setContentType("text/html");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (username.equals(USERNAME) && password.equals(PASSWORD)) {
			//Create cookie for username
			Cookie user = new Cookie("usernameCookie", req.getParameter("username"));
			
			//Set expiration date for cookie
			user.setMaxAge(60*60*24);
			
			//Add to response header
			resp.addCookie(user);
			
			resp.getWriter().printf("Welcome %s", username);
			
			System.out.println(user.getName());
			System.out.println(user.getValue());
		}
		else {
			resp.getWriter().println("Bad Login ");
			resp.getWriter().println("<a href=\"index.jsp\">Back</a>");
		}
		
	}
}
