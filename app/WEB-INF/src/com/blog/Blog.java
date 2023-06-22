package com.blog;

import com.user.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class Blog extends HttpServlet {
	int nextId = 0;
	ArrayList< BlogPost > bposts = new ArrayList< BlogPost >();

	private void displayBlogPost(BlogPost bp, PrintWriter writer) {
		writer.println("<h1>" + bp.getName() + "</h1>");
		writer.println("<article>");
		for (String paragraph : bp.getContent()) {
			writer.println("<p>" + paragraph + "</p>");
		}
		writer.println("</article>");
		writer.println("<p><a href=\"/app/user/" + bp.getOwnerId() + "\">Author: "
						+ getAuthorName(bp.getOwnerId()) + "</a></p>");
		writer.close();
	}

	private String getAuthorName(int id) {
		ServletContext context = getServletContext();
		List< UserInfo > users = (List < UserInfo >) context.getAttribute("users");
		for (UserInfo u : users) {
			if (u.getId() == id) {
				return u.getFirstname() + " " + u.getLastname();
			}
		}

		return Integer.toString(id);
	}

	private void listBlogPosts(PrintWriter writer) {
		writer.println("<h1>Blog Posts</h1>");
		writer.println("<ul>");
		for (BlogPost bp : bposts) {
			writer.println("<li><a href=\"/app/blog/" + bp.getId() + "\">" + bp.getName() + "</a></li>");
		}
		writer.println("</ul>");
		writer.println("<a href=\"/app/\">Back</a>");
		writer.close();
	}


	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
			listBlogPosts(res.getWriter());
		} else { // otherwise, interpret the path as an id
			BlogPost bp = bposts.stream().filter(d -> d.getId() == Integer.parseInt(req.getPathInfo().substring(1))).findAny().orElse(null);
			if (bp == null)
				res.setStatus(403, "No blogpost");
			displayBlogPost(bp, res.getWriter());
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String[] text  = req.getParameterValues("text");
		String[] owner = req.getParameterValues("owner");
		String[] name  = req.getParameterValues("name");
		if (text.length < 1 || owner.length < 1 || name.length < 1) {
			resp.setStatus(400);
			return;
		}

		bposts.add(new BlogPost(nextId++, Integer.parseInt(owner[0]), name[0], text));
		out.println("Blog post created");
	}

}
