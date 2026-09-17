package com.employee.servlet;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@SuppressWarnings("serial")
@WebServlet("/login")
public class loginServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(username.equals("admin") && password.equals("admin@123")) {
			HttpSession session = req.getSession();
			session.setAttribute("user", username);
			res.sendRedirect("index.html");
		}
		else {
			res.sendRedirect("login.html?error=1");
		}
	}
	
}
