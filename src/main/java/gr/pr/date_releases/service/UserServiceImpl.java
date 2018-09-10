package gr.pr.date_releases.service;

import gr.pr.date_releases.dao.GenericDao;
import gr.pr.date_releases.dao.UserDao;
import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private GenericDao genericDao;
	
	@Override
	public boolean hasUserFavoriteSeries(SeriesEntity series) {
		return series.getUsersFavorite().stream()
				.filter(user -> user.getUserName().equals(getLoggedInUser()))
				.count() > 0;
	}
	
	@Override
	public UserEntity getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = null;
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			userName = authentication.getName();
		}
		return userDao.getUserByUserName(userName);
	}
	
	@Override
	public boolean createUser(UserEntity user) {
		try {
			genericDao.save(user);
			return true;
		} catch (Exception e) {
//			TODO : fix different messages if email exists or userName
			System.out.println("User already exists");
			return false;
		}
	}
}
