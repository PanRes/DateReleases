package gr.pr.date_releases.service;

import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.UserEntity;

public interface UserService {
	
	boolean hasUserFavoriteSeries(SeriesEntity series);
	
	UserEntity getLoggedInUser();
}
