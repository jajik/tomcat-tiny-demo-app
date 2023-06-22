package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class Demo extends HttpServlet {
	public void doGet( HttpServletRequest req, HttpServletResponse res ) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		out.println("request properties");
		out.println("\t\tgetContextPath: " + req.getContextPath());
		out.println("\t\tgetServletPath: " + req.getServletPath());
		out.println("\t\tgetQueryString: " + req.getQueryString());
		out.println("\t\tgetPathInfo: " + req.getPathInfo());
		out.println("\t\tgetRequestedURI: " + req.getRequestURI());
		out.println("\t\tgetRequestedURL: " + req.getRequestURL());
		out.close();
	}
}
