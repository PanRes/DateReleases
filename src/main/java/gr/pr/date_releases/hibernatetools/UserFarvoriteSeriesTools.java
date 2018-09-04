package gr.pr.date_releases.hibernatetools;

public class UserFarvoriteSeriesTools{
/*

	public static boolean addRemoveUsersFavoritesFavorites(int seriesId, int userId){
		if(isUsersFavoriteSeries(seriesId, userId)){
			UsersFavoritesSeriesModel usersFavoritesSeries = getUsersFavoriteSeriesUserAndSeriesById(userId,seriesId);
			try {
				HibernateTools.deleteTableRow(usersFavoritesSeries);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		else{
			UsersFavoritesSeriesModel usersFavoritesSeries = new UsersFavoritesSeriesModel();
			usersFavoritesSeries.setUsersByUserId(UserTools.getUserById(userId));
			usersFavoritesSeries.setSeriesBySeriesId(SeriesTools.getSeriesById(seriesId));
			try {
				HibernateTools.insertEntity(usersFavoritesSeries);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	public static boolean isUsersFavoriteSeries(int seriesId, int userId){

		Session session = null;
		try {
			session = HibernateTools.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		Session session = null;
		try {
			session = HibernateTools.getSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
*/

}
