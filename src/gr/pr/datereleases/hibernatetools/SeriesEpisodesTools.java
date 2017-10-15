package gr.pr.datereleases.hibernatetools;

import gr.pr.datereleases.models.SeriesEpisodesModel;
import gr.pr.datereleases.models.SeriesModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.Query;
import java.util.*;

public class SeriesEpisodesTools {

    public static List<SeriesEpisodesModel> getSeriesEpisodeBySeriesId(int seriesId){
        Session session = HibernateTools.getSession();
        List<SeriesEpisodesModel> seriesEpisodes = null;
        if (seriesId == 0){
            seriesEpisodes = session.createCriteria(SeriesEpisodesModel.class).list();
        }
        else{
            SeriesModel series = SeriesTools.getSeriesById(seriesId);
            seriesEpisodes = session.createCriteria(SeriesEpisodesModel.class).
                    add(Restrictions.eq("seriesBySeriesId",series)).list();
        }
        System.out.println(seriesEpisodes);
        session.close();
        return seriesEpisodes;
    }

    public static int getSeriesEpisodesRowsCountBySeriesId(int seriesId){
        return SeriesEpisodesTools.getSeriesEpisodeBySeriesId(seriesId).size();
    }

    public static void insertMultipleSeriesEpisodes(List<SeriesEpisodesModel> seriesEpisodes) throws Exception {
        for (SeriesEpisodesModel seriesEpisode : seriesEpisodes) {
            System.out.println(seriesEpisode.getSeriesBySeriesId().toString());
            HibernateTools.insertEntity(seriesEpisode);
        }
    }

}
