package fr.acceis.forum.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProfilServlet
 */

public class ProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = request.getParameter("login");
		Object username = session.getAttribute("username");
		try {
			if(login != null && DAO.exist(login)) {
				String[] infos = new String[4];
				try {
					infos = DAO.getInformations(login);
				} catch (Exception e) {
					e.printStackTrace();
				}
				session.setAttribute("infos", infos);
				request.getRequestDispatcher("/WEB-INF/jsp/profil.jsp").forward(request, response);
			}
			else if (username != null) {
				response.sendRedirect("/forum/profil?login=" + username);
			}
			else {
				response.sendRedirect("/forum/home");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
