package gr.pr.date_releases.utils;

import gr.pr.date_releases.hibernatetools.SeriesEpisodesTools;
import gr.pr.date_releases.models.SeriesEpisodesModel;
import gr.pr.date_releases.models.SeriesModel;

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
