package fr.acceis.forum.servlet;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class UploadServlet
 */

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		HttpSession session = request.getSession();
		
		String username = session.getAttribute("username").toString();

		File avatar = new File("C:\\Users\\Sina Khoubi\\eclipse-workspace\\forum\\WebContent\\fichiers\\"+ username + ".png");
		List<FileItem> items = null;
		String nomFichier = null;
		
		try {
			items = upload.parseRequest(new ServletRequestContext(request));
			if (items.get(0).getSize() > 2000000) {
				request.setAttribute("badSize", "oui");
				request.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(request, response);
			}
			items.get(0).write(avatar);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Image image = ImageIO.read(avatar); //Retourne null si ce n'est pas une image
		nomFichier = items.get(0).getName(); //On vérifie qu'il n'y a pas d'extension comme .zip ou .php
		if (image == null || nomFichier.contains(".php") || nomFichier.contains(".zip")) {
			request.setAttribute("avatar", "nonok");
			request.getRequestDispatcher("/WEB-INF/jsp/profil.jsp").forward(request, response);
		}
		else {
			String path = "fichiers/" + username + ".png"; //Path à mettre à jour dans la BDD
			try {
				DAO.updateAvatar(username, path);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("avatar", "ok");
			request.getRequestDispatcher("/WEB-INF/jsp/profil.jsp").forward(request, response);
		}
	}
}
