package gr.pr.datereleases.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

@WebServlet(name = "LanguageServlet", value = "/LanguageServlet")
public class LanguageServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String language = request.getParameter("language");
		String redirect = request.getHeader("referer");
		// TODO: fix UTF8 encoding
		/*InputStream utf8in = getClass().getClassLoader().getResourceAsStream("/language_el.properties");
		Reader reader = new InputStreamReader(utf8in, "UTF-8");
		Properties props = new Properties();
		props.load(reader);*/
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.getSession().setAttribute("language",language);

		response.sendRedirect(redirect);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

	}
}
