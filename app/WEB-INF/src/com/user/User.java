package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;


public class User extends HttpServlet {
	int nextId = 0;

	private void displayUser(UserInfo usr, PrintWriter writer) {
		if (usr == null)
			writer.println("User not found!");
		else
			writer.println("    name: " + usr.getFirstname() + " " + usr.getLastname()
					+ "; id: " + Integer.toString(usr.getId())
					+ " age: " + Integer.toString(usr.getAge())); 
	}

	private ArrayList< UserInfo > getUsers() {
		ServletContext context = getServletContext();
		return (ArrayList< UserInfo >) context.getAttribute("users");
	}

	private void setUsers(ArrayList< UserInfo > updatedList) {
		getServletContext().setAttribute("users", updatedList);
	}

	private void listUsers(PrintWriter writer) {
		writer.println("<h1>Users</h1>");
		writer.println("<ul>");
		for (UserInfo usr : getUsers()) {
			writer.println("<li><a href=\"/app/user/" + usr.getId() + "\">" + usr.getFirstname() + " " + usr.getLastname() + "</a></li>");
		}
		writer.println("</ul>");
		writer.println("<a href=\"/app/\">Back</a>");
		writer.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		if (req.getPathInfo() == null || req.getPathInfo().equals("/"))
			listUsers(res.getWriter());
		else // otherwise, interpret the path as an id
			displayUser(getUsers().stream().filter(u -> u.getId() == Integer.parseInt(req.getPathInfo().substring(1))).findAny().orElse(null)
					, res.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PrintWriter out = resp.getWriter();
		String[] fns = req.getParameterValues("firstname");
		String[] lns = req.getParameterValues("lastname");
		String[] ags = req.getParameterValues("age");
		if (fns.length < 1 || lns.length < 1 || ags.length < 1) {
			resp.setStatus(400);
			return;
		}

		var users = getUsers();
		users.add(new UserInfo(nextId++, fns[0], lns[0], Integer.parseInt(ags[0])));
		setUsers(users);

		out.println("User created with id " + Integer.toString(nextId - 1));
	}

	public String getUserName(int id) {
		UserInfo u = getUsers().stream().filter(user -> user.getId() == id).findFirst().orElse(null);
		if (u != null)
			return u.getFirstname() + " " + u.getLastname();
		return null;
	}
}
