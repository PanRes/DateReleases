package gr.pr.datereleases.hibernatetools;


import gr.pr.datereleases.models.SeriesModel;
import javafx.scene.chart.XYChart;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SeriesTools {

    public static List<SeriesModel> getAllSeries(){
        Session session = HibernateTools.getSession();
        List<SeriesModel> allSeries = session.createCriteria(SeriesModel.class).list();
        session.close();
        return allSeries;
    }

    public static SeriesModel getSeriesByName(String seriesName){
        Session session = HibernateTools.getSession();
        SeriesModel series = (SeriesModel) session.createCriteria(SeriesModel.class).
                add(Restrictions.like("name",seriesName)).list().get(0);

        return series;
    }

    public static SeriesModel getSeriesById(int seriesId){
        Session session = HibernateTools.getSession();
        return session.get(SeriesModel.class,seriesId);
    }

}
