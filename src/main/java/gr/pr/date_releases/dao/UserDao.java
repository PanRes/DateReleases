
package gr.pr.date_releases.dao;
import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.UserEntity;

import java.util.List;

public interface UserDao {
	
	List<UserEntity> getAllUsers();
	UserEntity getUserByUserName(String userName);
	void removeFavoriteSeriesBySeries(SeriesEntity series);
	void removeFavoriteSeriesBySeriesId(int seriesId);
	void removeFavoriteSeriesBySeriesName(String seriesName);
	
}
