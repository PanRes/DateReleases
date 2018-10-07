package gr.pr.date_releases.service;

import gr.pr.date_releases.dao.GenericDao;
import gr.pr.date_releases.dao.RolesDao;
import gr.pr.date_releases.dao.UserDao;
import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.UserEntity;
import gr.pr.date_releases.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private GenericDao genericDao;

	@Autowired
	private RolesDao rolesDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public boolean hasUserFavoriteSeries(SeriesEntity series) {
		return series.getUsersFavorite().stream()
				.filter(user -> user.getUserName().equals(getLoggedInUserName()))
				.count() > 0;
	}
	
	@Override
	@Transactional
	public UserEntity getLoggedInUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = null;
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			userName = authentication.getName();
		}
		return userDao.getUserByUserNameOrEmail(userName);
	}
	
	@Override
	@Transactional
	public boolean createUser(UserEntity user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.addRole(rolesDao.getRoleByName(Roles.USER.toString()));
			//TODO : different message for duplicate email or username
			genericDao.save(user);
			return true;
		} catch (Exception e) {
//			TODO : fix different messages if email exists or userName
			System.out.println("User already exists");
			return false;
		}
	}
	
	@Override
	@Transactional
	public List<UserEntity> getAllUsers() {
		return userDao.getAllUsers();
	}
	
	@Override
	@Transactional
	public UserEntity getLoggedInUser() {
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();
			
			UserEntity user = userDao.getUserByUserNameOrEmail(userName);
			
			return user;
		} catch (Exception e) {
			System.out.println("Logged in User was not found");
		}
		
		return null;
	}
	
	@Override
	@Transactional
	public boolean updateUser(UserEntity user) throws Exception {
		if (userDao.getUserByUserName(user.getUserName()) != null) {
			throw new Exception("duplicateUserName");
		}
		else if (userDao.getUserByEmail(user.getEmail()) != null) {
			throw new Exception("duplicateEmail");
		}
		
		try {
			genericDao.update(user);
		}
		catch (Exception e) {
			throw new Exception("userUpdateFail");
		}
		
		return true;
		
	}
}
