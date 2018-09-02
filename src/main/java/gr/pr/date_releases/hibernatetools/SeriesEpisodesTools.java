package gr.pr.date_releases.hibernatetools;

import gr.pr.date_releases.models.SeriesEpisodesModel;
import gr.pr.date_releases.models.SeriesModel;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SeriesEpisodesTools {

	public static List<SeriesEpisodesModel> getSeriesEpisodeBySeriesId(int seriesId){
		Session session = null;
		try {
			session = HibernateTools.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.beginTransaction();
		List<SeriesEpisodesModel> seriesEpisodes = null;
		if (seriesId == -1){
			seriesEpisodes = session.createCriteria(SeriesEpisodesModel.class).list();
		}
		else{
			SeriesModel series = SeriesTools.getSeriesById(seriesId);
			seriesEpisodes = session.createCriteria(SeriesEpisodesModel.class).
					add(Restrictions.eq("seriesBySeriesId",series)).list();
		}
		/*
		* Leave System.out.println be there, alternative it is not return anything, needs investigation
		* */
		System.out.println(seriesEpisodes);
		session.close();
		return seriesEpisodes;
	}

	public static int getSeriesEpisodesRowsCountBySeriesId(int seriesId){
		return SeriesEpisodesTools.getSeriesEpisodeBySeriesId(seriesId).size();
	}

	public static int getSeriesEpisodeIdBySeriesSeasonAndEpisode(SeriesModel series, int season, int episode){
		Session session = null;
		try {
			session = HibernateTools.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.beginTransaction();
		int seriesEpisodeId = ((SeriesEpisodesModel)
				session.createCriteria(SeriesEpisodesModel.class).
				add(Restrictions.eq("seriesBySeriesId",series)).
				add(Restrictions.eq("season",season)).
				add(Restrictions.eq("episode",episode)).list().get(0)).getSeriesEpisodesId();
		session.close();
		return seriesEpisodeId;

	}

	public static String updatedSeriesEpisodeNotes(int seriesEpisodeId, String newNotes){
		Session session = null;
		try {
			session = HibernateTools.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.beginTransaction();
		SeriesEpisodesModel seriesEpisode = session.get(SeriesEpisodesModel.class,seriesEpisodeId);
		String notes = null;
		if (seriesEpisode.getNotes() == null) {
			notes = newNotes;
		}
		else {
			notes = seriesEpisode.getNotes() + ", " + newNotes;
		}
		session.close();
		return notes;
	}

	public static void insertMultipleSeriesEpisodes(List<SeriesEpisodesModel> seriesEpisodes) throws Exception {
		for (SeriesEpisodesModel seriesEpisode : seriesEpisodes) {
			insertOrUpdateSeriesEpisode(seriesEpisode);
		}
	}

	public static void insertOrUpdateSeriesEpisode(SeriesEpisodesModel seriesEpisode){
		if(seriesEpisodeExists(seriesEpisode)){
			int seriesEpisodeId = getSeriesEpisodeIdBySeriesSeasonAndEpisode(seriesEpisode.getSeriesBySeriesId(),
					seriesEpisode.getSeason(),seriesEpisode.getEpisode());
			seriesEpisode.setSeriesEpisodesId(seriesEpisodeId);
			seriesEpisode.setNotes(updatedSeriesEpisodeNotes(seriesEpisodeId,seriesEpisode.getNotes()));
			try {
				HibernateTools.updateEntity(seriesEpisode);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				HibernateTools.insertEntity(seriesEpisode);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void deleteSeriesEpisodeRow(int seriesEpisodeId){
		Session session = null;
		try {
			session = HibernateTools.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.beginTransaction();
		SeriesEpisodesModel seriesEpisode = session.load(SeriesEpisodesModel.class,seriesEpisodeId);
		try {
			HibernateTools.deleteTableRow(seriesEpisode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.flush();
		session.close();
	}


	public static boolean seriesEpisodeExists(SeriesEpisodesModel seriesEpisode){
		List<SeriesEpisodesModel> seriesEpisodesModels = getSeriesEpisodeBySeriesId(0);
		for (SeriesEpisodesModel seriesEpisodesModel : seriesEpisodesModels) {
			if(seriesEpisode.equals(seriesEpisodesModel)){
				return true;
			}
		}
		return false;
	}

	public static SeriesEpisodesModel getSeriesEpisodeById(int seriesEpisodeId){
		Session session = null;
		try {
			session = HibernateTools.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.beginTransaction();
		SeriesEpisodesModel seriesEpisode = session.get(SeriesEpisodesModel.class,seriesEpisodeId);
		System.out.println(seriesEpisode);
		session.flush();
		session.close();
		return seriesEpisode;
	}

}
