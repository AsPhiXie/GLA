package fr.acceis.forum.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PageReponse
 */

public class PageReponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageReponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();

		String idFil = session.getAttribute("idFil").toString();
		if(session.getAttribute("username") == null) {
			session.setAttribute("filnum", idFil);
			response.sendRedirect("/forum/login");
		}
		else {
			request.getRequestDispatcher("/WEB-INF/jsp/response.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idFil = session.getAttribute("idFil").toString();
		String corpsMessage = request.getParameter("corps");
		String name = session.getAttribute("username").toString();

		try {
			DAO.newResponse(idFil, corpsMessage, name);
			DAO.incRep(idFil);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("/forum/thread?idFil=" + idFil);
	}
}
