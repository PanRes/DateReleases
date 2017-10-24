package gr.pr.datereleases.hibernatetools;

import gr.pr.datereleases.models.SeriesModel;
import gr.pr.datereleases.models.UsersFavoritesSeriesModel;
import gr.pr.datereleases.models.UsersModel;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class UserFarvoriteSeriesTools{

    public static void addRemoveUsersFavoritesFavorites(int seriesId, int userId){
        if(isUsersFavoriteSeries(seriesId, userId)){
            UsersFavoritesSeriesModel usersFavoritesSeries = getUsersFavoriteSeriesUserAndSeriesById(userId,seriesId);
            HibernateTools.deleteTableRow(usersFavoritesSeries);
        }
        else{
            UsersFavoritesSeriesModel usersFavoritesSeries = new UsersFavoritesSeriesModel();
            usersFavoritesSeries.setUsersByUserId(UserTools.getUserById(userId));
            usersFavoritesSeries.setSeriesBySeriesId(SeriesTools.getSeriesById(seriesId));
            HibernateTools.insertEntity(usersFavoritesSeries);
        }
    }

    public static boolean isUsersFavoriteSeries(int seriesId, int userId){
        try{
            Session session = HibernateTools.getSession();
            session.beginTransaction();
            SeriesModel series = SeriesTools.getSeriesById(seriesId);
            UsersModel user = UserTools.getUserById(userId);
            UsersFavoritesSeriesModel usersFavoritesSeriesModel = (UsersFavoritesSeriesModel)
                    session.createCriteria(UsersFavoritesSeriesModel.class).
                    add(Restrictions.eq("seriesBySeriesId",series)).
                    add(Restrictions.eq("usersByUserId",user)).list().get(0);
            session.flush();
            session.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
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
