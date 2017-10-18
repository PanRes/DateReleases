package gr.pr.datereleases.servlets;

import gr.pr.datereleases.hibernatetools.SeriesEpisodesTools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteSeresEpisodeRowServlet", value = "/DeleteSeresEpisodeRowServlet")
public class DeleteSeresEpisodeRowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int seriesEpisodeId = Integer.valueOf(request.getParameter("seriesEpisodeId"));
        int seriesId = Integer.valueOf(request.getParameter("seriesId"));
        SeriesEpisodesTools.deleteSeriesEpisodeRow(seriesEpisodeId);
        response.sendRedirect("/viewSchedule?seriesId=" + seriesId);
    }
}
