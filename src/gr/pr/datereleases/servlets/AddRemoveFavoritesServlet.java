package gr.pr.datereleases.servlets;

import gr.pr.datereleases.hibernatetools.UserFarvoriteSeriesTools;
import gr.pr.datereleases.models.SeriesModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddRemoveFavoritesServlet", value = "/AddRemoveFavoritesServlet")
public class AddRemoveFavoritesServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession();

        int seriesId = Integer.valueOf(request.getParameter("seriesId"));
        int userId = (int) session.getAttribute("userId");

        boolean removedFromFavorite = UserFarvoriteSeriesTools.addRemoveUsersFavoritesFavorites(seriesId,userId);
        String redirectPage = request.getHeader("referer");
        response.sendRedirect(redirectPage);
    }
}
