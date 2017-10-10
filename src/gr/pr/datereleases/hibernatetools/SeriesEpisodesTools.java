package gr.pr.datereleases.hibernatetools;

import gr.pr.datereleases.models.SeriesEpisodesModel;
import gr.pr.datereleases.models.SeriesModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Query;
import java.util.*;

public class SeriesEpisodesTools {

    public static List<SeriesEpisodesModel> getAllSeries(){
        Session session = HibernateTools.getSession();
        Query query = session.getNamedQuery("SeriesEpisodesModel.findAll");

        List<SeriesEpisodesModel> allSeries = query.getResultList();
        System.out.println(allSeries);
        session.close();

        return allSeries;
    }

    public static List<SeriesEpisodesModel> getSeriesById(int seriesId){
        Session session = HibernateTools.getSession();
        SeriesModel series = null;
        List<SeriesEpisodesModel> seriesById = null;
        if (seriesId == 0){
            seriesById = session.createCriteria(SeriesEpisodesModel.class).list();
        }
        else{
            series = (SeriesModel) session.get(SeriesModel.class,seriesId);
            seriesById = session.createCriteria(SeriesEpisodesModel.class).
                    add(Restrictions.eq("seriesBySeriesId",series)).list();
        }

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

}
