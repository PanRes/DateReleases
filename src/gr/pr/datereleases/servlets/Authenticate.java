package gr.pr.datereleases.servlets;

import gr.pr.datereleases.hibernatetools.UserTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Authenticate",value = "/Authenticate")
public class Authenticate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        if(UserTools.isValidUser(userName,password)){
            session.setAttribute("user",userName);
            response.sendRedirect("/mainMenu");
        }
        else{
            request.setAttribute("notValid",true);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }
}
