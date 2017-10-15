package gr.pr.datereleases.utils;

import gr.pr.datereleases.hibernatetools.HibernateTools;
import gr.pr.datereleases.hibernatetools.SeriesEpisodesTools;
import gr.pr.datereleases.hibernatetools.SeriesTools;
import gr.pr.datereleases.models.SeriesEpisodesModel;
import gr.pr.datereleases.models.SeriesModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddDatesUtil {

    public static boolean addSingleDate(HttpServletRequest request){

        int seriesId = Integer.valueOf(request.getParameter("seriesId"));
        int season = Integer.valueOf(request.getParameter("season"));
        int episode = Integer.valueOf(request.getParameter("episode"));
        String notes = request.getParameter("notes");
        String dateString = request.getParameter("date");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = new Date(sdf.parse(dateString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SeriesModel series = SeriesTools.getSeriesById(seriesId);
        SeriesEpisodesModel seriesEpisodes = new SeriesEpisodesModel();

        seriesEpisodes.setSeriesBySeriesId(series);
        seriesEpisodes.setSeason(season);
        seriesEpisodes.setEpisode(episode);
        seriesEpisodes.setNotes(notes);
        seriesEpisodes.setReleaseDate(date);

        try {
            HibernateTools.insertEntity(seriesEpisodes);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
}
