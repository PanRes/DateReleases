package gr.pr.datereleases.servlets;

import gr.pr.datereleases.hibernatetools.HibernateTools;
import gr.pr.datereleases.hibernatetools.UserTools;
import gr.pr.datereleases.models.UsersModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ChangePasswordServlet", value = "/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		String oldPassword = request.getParameter("oldPassword");
		String password = request.getParameter("password");
		String passwordVerify = request.getParameter("passwordVerify");
		HttpSession httpSession = request.getSession();

		UsersModel user = (UsersModel) httpSession.getAttribute("userId");

		if(!oldPassword.equals(user.getPassword())){
			request.setAttribute("success","wrongPassword");
			request.getRequestDispatcher("/changeUserPassword").forward(request,response);
		}
		else if (!password.equals(passwordVerify)){
			request.setAttribute("success","differentPasswords");
			request.getRequestDispatcher("/changeUserPassword").forward(request,response);
		}
		else{
			user.setPassword(password);
			HibernateTools.updateEntity(user);
			request.setAttribute("success","success");
			request.getRequestDispatcher("/changeUserPassword").forward(request,response);
		}


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.sendRedirect("/changeUserPassword");
	}
}
