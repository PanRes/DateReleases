package gr.pr.datereleases.servlets;

import gr.pr.datereleases.hibernatetools.SeriesEpisodesTools;
import gr.pr.datereleases.hibernatetools.SeriesTools;
import gr.pr.datereleases.models.SeriesEpisodesModel;
import gr.pr.datereleases.models.SeriesModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "AddDateServlet", value = "/AddDateServlet")
public class AddDateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int seriesId = Integer.valueOf(request.getParameter("seriesId"));
        int season = Integer.valueOf(request.getParameter("season"));
        int episode = Integer.valueOf(request.getParameter("episode"));
        String dateString = request.getParameter("date");
        boolean success = false;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = new Date(sdf.parse(dateString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SeriesModel series = SeriesTools.getSeriesById(seriesId);
        SeriesEpisodesModel seriesEpisodes = new SeriesEpisodesModel();

        seriesEpisodes.setSeriesEpisodesId(1);
        seriesEpisodes.setSeriesBySeriesId(series);
        seriesEpisodes.setSeason(season);
        seriesEpisodes.setEpisode(episode);
        seriesEpisodes.setReleaseDate(date);

        try {
            SeriesEpisodesTools.insertSeriesEpisode(seriesEpisodes);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("success",success);
        response.sendRedirect("/jsps/addDate.jsp");

//        System.out.println(seriesId);
//        System.out.println(season);
//        System.out.println(episode);
//        System.out.println(date);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/jsps/addDate.jsp");

    }
}
