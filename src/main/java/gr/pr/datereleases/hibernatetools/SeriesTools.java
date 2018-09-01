package gr.pr.datereleases.hibernatetools;


import gr.pr.datereleases.models.SeriesModel;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SeriesTools {

    public static List<SeriesModel> getAllSeries(){
        Session session = null;
        try {
            session = HibernateTools.getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.beginTransaction();
        List<SeriesModel> allSeries = session.createCriteria(SeriesModel.class).list();
        session.close();
        return allSeries;
    }

    public static SeriesModel getSeriesByName(String seriesName){
        Session session = null;
        try {
            session = HibernateTools.getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.beginTransaction();
        SeriesModel series = new SeriesModel();
        try{
            series = (SeriesModel) session.createCriteria(SeriesModel.class).
                    add(Restrictions.like("name",seriesName)).list().get(0);
        }
        catch (IndexOutOfBoundsException e){
            series.setName(seriesName);
            series.setEnded(false);
            try {
                HibernateTools.insertEntity(series);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            series = (SeriesModel) session.createCriteria(SeriesModel.class).
                    add(Restrictions.like("name",seriesName)).list().get(0);
        }
        session.close();

        return series;
    }

    public static String getSeriesNameBySeriesId(int seriesId){
        Session session = null;
        try {
            session = HibernateTools.getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.beginTransaction();
        String seriesName = session.get(SeriesModel.class,seriesId).getName();
        session.flush();
        session.close();
        return seriesName;
    }

    public static SeriesModel getSeriesById(int seriesId){
        Session session = null;
        try {
            session = HibernateTools.getSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.beginTransaction();
        SeriesModel seriesModel = session.get(SeriesModel.class,seriesId);
        session.flush();
        session.close();
        return seriesModel;
    }

}
