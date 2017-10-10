package gr.pr.datereleases.hibernatetools;


import gr.pr.datereleases.models.SeriesModel;
import org.hibernate.Session;

import java.util.List;

public class SeriesTools {

    public static List<SeriesModel> getAllSeries(){
        Session session = HibernateTools.getSession();
        List<SeriesModel> allSeries = session.createCriteria(SeriesModel.class).list();
        session.close();
        return allSeries;
    }

    public static SeriesModel getSeriesById(int seriesId){
        Session session = HibernateTools.getSession();
        return session.get(SeriesModel.class,seriesId);
    }

}
