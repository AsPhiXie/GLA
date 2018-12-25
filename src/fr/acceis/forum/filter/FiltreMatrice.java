package fr.acceis.forum.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltreMatrice
 */
public class FiltreMatrice implements Filter {

	//0 -> Invité
	//1 -> Utilisateur
	//2 -> Modérateur
	//3 -> Administrateur

	//0 -> Lire un fil
	//1 -> Ajouter un message
	//2 -> Accéder au profil
	//3 -> Supprimer un message

	//1 si droit accordé, 0 sinon

	private static boolean[][] RBAC = {{true, false, false, false}, {true, true, true, false}, {true, true, true, true}, {true, true, true, true}};
	/**
	 * Default constructor. 
	 */
	public FiltreMatrice() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	private static boolean access(int role, int droit) {
		return RBAC[role][droit];
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String user = null;
		int role = -1;

		//On caste les objets request et response
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;

		//On récupère la session HTTP
		HttpSession session = req.getSession();

		//On récupère l'identité de l'utilisateur en session
		if(session.getAttribute("username") != null) {
			user = session.getAttribute("username").toString();
		}
		if(session.getAttribute("role") != null ) {
			role = Integer.parseInt(session.getAttribute("role").toString());
		}
		//On lit l'URI de la ressource demandé
		String URI = req.getRequestURI();

		switch(URI) {
		case "/forum/profil": 
			if(user == null || role < 0 || !access(role, 2)) { 
				rep.sendRedirect("/forum/home?nondroit=oui"); 
			}else { 
				chain.doFilter(request, response);
			}
			break;

		case "/forum/supprimer": 
			if(role < 0 || !access(role, 3)) {
				rep.sendRedirect("/forum/home?nondroit=oui"); 
			}else { 
				chain.doFilter(request, response);
			}				
			break;
		
		case "/forum/newTopic":
		case "/forum/response": 
			if(role < 0 || !access(role, 1)) {
				rep.sendRedirect("/forum/home?nondroit=oui"); 
			}else { 
				chain.doFilter(request, response);
			}				
			break;
			
		case "/forum/thread": 
			if(role < 0 || !access(role, 0)) {
				rep.sendRedirect("/forum/home?nondroit=oui"); 
			}else { 
				chain.doFilter(request, response);
			}				
			break;

		default: chain.doFilter(request, response);
		}
		// pass the request along the filter chain

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
}
