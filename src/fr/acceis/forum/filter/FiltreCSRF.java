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

public class FiltreCSRF implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	private String genToken() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			int nb = (int)(alphabet.length() * Math.random());
			res.append(alphabet.charAt(nb));
		}
		return res.toString();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		if (req.getMethod().equals("POST")) {

			long expi = (long) session.getAttribute("csrfExpiration");

			if (expi >= System.currentTimeMillis()) {
				String getToken = session.getAttribute("csrfToken").toString();
				String originalToken = req.getParameter("csrfToken").toString();

				if (!getToken.equals(originalToken)) {
					rep.sendRedirect("/forum/home?token=oui"); 
				}
				else { 
					chain.doFilter(request, response);
				}
			}
			else {
				rep.sendRedirect("/forum/home?ExpiToken=oui");
			}
		}
		else {

			long expiration = System.currentTimeMillis() + 900000; //15mn
			String token = genToken();
			session.setAttribute("csrfToken", token);
			session.setAttribute("csrfExpiration", expiration);
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}
}
