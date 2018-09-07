package gr.pr.date_releases.service;

import gr.pr.date_releases.dao.UserDao;
import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserServiceImpl {
	
	@Autowired
	private UserDao userDao;
	
	public boolean hasUserFavoriteSeries(SeriesEntity series) {
		return series.getUsersFavorite().stream()
				.filter(user -> user.getUserName().equals(getLoggedInUser()))
				.count() > 0;
	}
	
	public UserEntity getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = null;
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			userName = authentication.getName();
		}
		return userDao.getUserByUserName(userName);
	}
}
