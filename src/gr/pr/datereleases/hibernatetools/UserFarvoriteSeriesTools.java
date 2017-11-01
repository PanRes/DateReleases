package gr.pr.datereleases.hibernatetools;

import gr.pr.datereleases.models.SeriesModel;
import gr.pr.datereleases.models.UsersFavoritesSeriesModel;
import gr.pr.datereleases.models.UsersModel;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.List;


public class UserFarvoriteSeriesTools{

    public static boolean addRemoveUsersFavoritesFavorites(int seriesId, int userId){
        if(isUsersFavoriteSeries(seriesId, userId)){
            UsersFavoritesSeriesModel usersFavoritesSeries = getUsersFavoriteSeriesUserAndSeriesById(userId,seriesId);
            HibernateTools.deleteTableRow(usersFavoritesSeries);
            return true;
        }
        else{
            UsersFavoritesSeriesModel usersFavoritesSeries = new UsersFavoritesSeriesModel();
            usersFavoritesSeries.setUsersByUserId(UserTools.getUserById(userId));
            usersFavoritesSeries.setSeriesBySeriesId(SeriesTools.getSeriesById(seriesId));
            HibernateTools.insertEntity(usersFavoritesSeries);
            return false;
        }
    }

    public static boolean isUsersFavoriteSeries(int seriesId, int userId){

        Session session = HibernateTools.getSession();
        session.beginTransaction();
        SeriesModel series = SeriesTools.getSeriesById(seriesId);
        UsersModel user = UserTools.getUserById(userId);
        Query query = session.createQuery("from UsersFavoritesSeriesModel " +
                "where usersByUserId=:u and seriesBySeriesId=:s");
        query.setParameter("s",series);
        query.setParameter("u",user);
        List<UsersFavoritesSeriesModel> usersFavoritesSeriesModels = query.list();
        session.flush();
        session.close();
        if(usersFavoritesSeriesModels.isEmpty()){
            return false;
        }
        return true;

    }

    public static UsersFavoritesSeriesModel getUsersFavoriteSeriesUserAndSeriesById(int userId,int seriesId){
        Session session = HibernateTools.getSession();
        session.beginTransaction();
        UsersModel user = session.get(UsersModel.class,userId);
        SeriesModel series = session.get(SeriesModel.class,seriesId);
        UsersFavoritesSeriesModel userFavoritesSeries = (UsersFavoritesSeriesModel)
                session.createCriteria(UsersFavoritesSeriesModel.class).
                add(Restrictions.eq("seriesBySeriesId",series)).
                add(Restrictions.eq("usersByUserId",user)).list().get(0);
        session.close();
        return userFavoritesSeries;
    }

}
