package gr.pr.datereleases.utils;

import gr.pr.datereleases.hibernatetools.SeriesEpisodesTools;
import gr.pr.datereleases.hibernatetools.SeriesTools;
import gr.pr.datereleases.models.SeriesEpisodesModel;
import gr.pr.datereleases.models.SeriesModel;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        SeriesEpisodesModel seriesEpisode = new SeriesEpisodesModel();

        seriesEpisode.setSeriesBySeriesId(series);
        seriesEpisode.setSeason(season);
        seriesEpisode.setEpisode(episode);
        seriesEpisode.setNotes(notes);
        seriesEpisode.setReleaseDate(date);


        try {
            SeriesEpisodesTools.insertOrUpdateSeriesEpisode(seriesEpisode);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

}
