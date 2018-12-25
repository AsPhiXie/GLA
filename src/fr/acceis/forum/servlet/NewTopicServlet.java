package fr.acceis.forum.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NewTopicServlet
 */

public class NewTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewTopicServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(session.getAttribute("username") == null) {
			response.sendRedirect("/forum/login");
		}
		else {
			request.getRequestDispatcher("/WEB-INF/jsp/newtopic.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String topicName = request.getParameter("name");
		String topicCorps = request.getParameter("corps");
		String auteur = session.getAttribute("username").toString();

		try {
			DAO.newTopic(topicName, topicCorps, auteur);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/forum/home");
	}
}
