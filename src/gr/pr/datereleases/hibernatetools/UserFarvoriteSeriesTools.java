package gr.pr.datereleases.hibernatetools;

import com.sun.org.apache.regexp.internal.RE;
import gr.pr.datereleases.models.SeriesModel;
import gr.pr.datereleases.models.UsersFavoritesSeriesModel;
import gr.pr.datereleases.models.UsersModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserFarvoriteSeriesTools{

    public static void addRemoveUsersFavoritesFavorites(int seriesId, int userId){
        if(isUsersFavoriteSeries(seriesId, userId)){
            UsersFavoritesSeriesModel usersFavoritesSeries = getUsersFavoriteSeriesById(userId);
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

    public static UsersFavoritesSeriesModel getUsersFavoriteSeriesById(int userFavoritesSeriesId){
        Session session = HibernateTools.getSession();
        session.beginTransaction();
        UsersFavoritesSeriesModel userFavoritesSeries = session.get(UsersFavoritesSeriesModel.class,userFavoritesSeriesId);
        session.close();
        return userFavoritesSeries;
    }

}
