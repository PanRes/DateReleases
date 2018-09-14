package gr.pr.date_releases.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Authenticate",value = "/Authenticate")
public class Authenticate extends HttpServlet {
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String page = request.getParameter("page");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		if (page == "" || page == null) {
			page = "/";
		}

		UsersModel user = UserTools.isValidUser(userName,password);
		if(user != null){
			session.setAttribute("user",user);
			response.sendRedirect(page);
		}
		else {
			response.sendRedirect("/login?wrongUser=true");
		}
	}
*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/");
	}
}
