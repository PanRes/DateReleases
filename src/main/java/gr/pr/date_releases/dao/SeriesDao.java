package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.UserEntity;

import java.util.List;

public interface SeriesDao {
	
	List<SeriesEntity> getAllSeriesById();
	SeriesEntity getSeriesById(int id);
	SeriesEntity getSeriesByName(String name);
	String getSeriesNameBySeriesId(int id);
	void removeUserFavoriteByUser(UserEntity user);
	void removeUserFavoriteByUserId(int userId);
	void removeUserFavoriteByUserName(String userName);
}
