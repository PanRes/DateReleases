package gr.pr.datereleases.hibernatetools;

import gr.pr.datereleases.models.SeriesEpisodesModel;
import gr.pr.datereleases.models.SeriesModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Query;
import java.util.*;

public class SeriesEpisodesTools {

    public static List<SeriesEpisodesModel> getSeriesEpisodeById(int seriesId){
        Session session = HibernateTools.getSession();
        List<SeriesEpisodesModel> seriesById = null;
        if (seriesId == 0){
            seriesById = session.createCriteria(SeriesEpisodesModel.class).list();
        }
        else{
            SeriesModel series = SeriesTools.getSeriesById(seriesId);
            seriesById = session.createCriteria(SeriesEpisodesModel.class).
                    add(Restrictions.eq("seriesBySeriesId",series)).list();
        }
        System.out.println(seriesById);
        session.close();
        return seriesById;
    }

    public static void insertSeriesEpisode(SeriesEpisodesModel seriesEpisode) throws Exception{
        Session session = HibernateTools.getSession();

        Transaction transaction = session.beginTransaction();
        session.save(seriesEpisode);
        transaction.commit();
        session.close();
    }

    public static void insertMultipleSeriesEpisodes(List<SeriesEpisodesModel> seriesEpisodes) throws Exception {
        for (SeriesEpisodesModel seriesEpisode : seriesEpisodes) {
            SeriesEpisodesTools.insertSeriesEpisode(seriesEpisode);
        }
    }

}
