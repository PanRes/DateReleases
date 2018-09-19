package gr.pr.date_releases.service;

import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.UserEntity;

import java.util.List;

public interface UserService {
	
	boolean hasUserFavoriteSeries(SeriesEntity series);
	UserEntity getLoggedInUserName();
	boolean createUser(UserEntity user);
	List<UserEntity> getAllUsers();
}
