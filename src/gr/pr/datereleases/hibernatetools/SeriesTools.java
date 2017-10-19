package gr.pr.datereleases.hibernatetools;


import gr.pr.datereleases.models.SeriesModel;
import javafx.scene.chart.XYChart;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SeriesTools {

    public static List<SeriesModel> getAllSeries(){
        Session session = HibernateTools.getSession();
        session.beginTransaction();
        List<SeriesModel> allSeries = session.createCriteria(SeriesModel.class).list();
        session.close();
        return allSeries;
    }

    public static SeriesModel getSeriesByName(String seriesName){
        Session session = HibernateTools.getSession();
        session.beginTransaction();
        SeriesModel series = new SeriesModel();
        try{
            series = (SeriesModel) session.createCriteria(SeriesModel.class).
                    add(Restrictions.like("name",seriesName)).list().get(0);
        }
        catch (IndexOutOfBoundsException e){
            series.setName(seriesName);
            series.setEnded(false);
            HibernateTools.insertEntity(series);
            series = (SeriesModel) session.createCriteria(SeriesModel.class).
                    add(Restrictions.like("name",seriesName)).list().get(0);
        }
        session.close();

        return series;
    }

    public static String getSeriesNameBySeriesId(int seriesId){
        Session session = HibernateTools.getSession();
        return session.get(SeriesModel.class,seriesId).getName();
    }

    public static SeriesModel getSeriesById(int seriesId){
        Session session = HibernateTools.getSession();
        session.beginTransaction();
        SeriesModel seriesModel = session.get(SeriesModel.class,seriesId);
        session.close();
        return seriesModel;
    }

}
