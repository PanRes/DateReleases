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
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        if (page == "" || page == null) {
            page = "/mainMenu";
        }

        int userId = UserTools.isValidUser(userName,password);
        if(userId > 0){
            session.setAttribute("user",userName);
            session.setAttribute("userId",userId);
            response.sendRedirect(page);
        }
        else {
            response.sendRedirect("/?wrongUser=true");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/");
    }
}
