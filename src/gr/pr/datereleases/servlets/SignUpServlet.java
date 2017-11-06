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

@WebServlet(name = "SignUpServlet", value = "/SignUpServlet")
public class SignUpServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String passwordVerify = request.getParameter("passwordVerify");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");


		if(UserTools.emailExists(email)){
			response.sendRedirect("/signUp?signUpError=duplicateEmail");
		}
		else if(UserTools.userNameExists(userName)){
			response.sendRedirect("/signUp?signUpError=duplicateUserName");
		}
		else if(!password.equals(passwordVerify)){
			response.sendRedirect("/signUp?signUpError=passwordNotMatch");
		}
		else{
			UsersModel user = new UsersModel();
			user.setUserName(userName);
			user.setEmail(email);
			user.setPassword(password);
			user.setFirstName(firstName);
			user.setMiddleName(middleName);
			user.setLastName(lastName);
			HibernateTools.insertEntity(user);
			request.setAttribute("userName",user);
			request.setAttribute("password",password);
			request.getRequestDispatcher("/Authenticate").forward(request,response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.sendRedirect("");
	}
}