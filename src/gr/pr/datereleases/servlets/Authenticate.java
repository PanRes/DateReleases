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
        String page = request.getParameter("page");
        System.out.println(page);
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        if (page == "" || page == null) {
            page = "/mainMenu";
        }

        if(UserTools.isValidUser(userName,password)){
            session.setAttribute("user",userName);
            response.sendRedirect(page);
        }
        else {
            request.setAttribute("wrongUser", true);
            request.getServletContext().getRequestDispatcher("/").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }
}
