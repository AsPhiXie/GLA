package fr.acceis.forum.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccueilServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String nondroit = req.getParameter("nondroit");
		String token = req.getParameter("token");
		String ExpiToken = req.getParameter("ExpiToken");
		String badSize = req.getParameter("badSize");
		if(nondroit != null) {
			req.setAttribute("nondroit", "oui");
		}
		if(token != null) {
			req.setAttribute("token", "oui");
		}
		if(ExpiToken != null) {
			req.setAttribute("ExpiToken", "oui");
		}
		if(badSize != null) {
			req.setAttribute("badSize", "oui");
		}
		if(session.getAttribute("username") == null) {
			session.setAttribute("role", 0);
		}
		String role = session.getAttribute("role").toString();
		try {
			req.setAttribute("fil", DAO.getAllFil());
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("/WEB-INF/jsp/threads.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
