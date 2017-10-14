package gr.pr.datereleases.servlets;

import gr.pr.datereleases.hibernatetools.SeriesEpisodesTools;
import gr.pr.datereleases.models.SeriesEpisodesModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ScheduleServlet",value = "/ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int seriesId = Integer.valueOf(request.getParameter("seriesId"));
        List<SeriesEpisodesModel> seriesEpisodesById = SeriesEpisodesTools.getSeriesEpisodeBySeriesId(seriesId);
        session.setAttribute("seriesEpisodesById",seriesEpisodesById);
        response.sendRedirect("/viewSchedule");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.sendRedirect("/mainMenu");
        doPost(request,response);
    }
}
