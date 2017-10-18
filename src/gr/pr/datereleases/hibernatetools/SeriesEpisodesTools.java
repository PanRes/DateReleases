package gr.pr.datereleases.hibernatetools;

import gr.pr.datereleases.models.SeriesEpisodesModel;
import gr.pr.datereleases.models.SeriesModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.*;

public class SeriesEpisodesTools {

    public static List<SeriesEpisodesModel> getSeriesEpisodeBySeriesId(int seriesId){
        Session session = HibernateTools.getSession();
        session.beginTransaction();
        List<SeriesEpisodesModel> seriesEpisodes = null;
        if (seriesId == 0){
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
        Session session = HibernateTools.getSession();
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
        Session session = HibernateTools.getSession();
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
            HibernateTools.updateEntity(seriesEpisode);
        }
        else{
            HibernateTools.insertEntity(seriesEpisode);
        }
    }

    public static void deleteSeriesEpisodeRow(int seriesEpisodeId){
        Session session = HibernateTools.getSession();
        SeriesEpisodesModel seriesEpisode = session.load(SeriesEpisodesModel.class,seriesEpisodeId);
        Transaction tx = session.beginTransaction();
        session.delete(seriesEpisode);
        session.flush();
        tx.commit();
        session.close();
    }


    public static boolean seriesEpisodeExists(SeriesEpisodesModel seriesEpisode){
        List<SeriesEpisodesModel> seriesEpisodesModels = getSeriesEpisodeBySeriesId(0);
        for (SeriesEpisodesModel seriesEpisodesModel : seriesEpisodesModels) {
            System.out.println("seriesEpisodesModel" + seriesEpisodesModel.getSeriesBySeriesId());
            System.out.println("seriesEpisode" + seriesEpisode.getSeriesBySeriesId());
            System.out.println(seriesEpisode.getSeriesBySeriesId().equals(seriesEpisodesModel.getSeriesBySeriesId()) +
            " " + seriesEpisode.getSeriesBySeriesId().hashCode() + " " + seriesEpisodesModel.getSeriesBySeriesId().hashCode());
            if(seriesEpisode.equals(seriesEpisodesModel)){
                return true;
            }
        }
        return false;
    }

}
