package gr.pr.date_releases.service;

import gr.pr.date_releases.dao.UserDao;
import gr.pr.date_releases.entity.RolesEntity;
import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserAuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userDao.getUserByUserName(username);
		
		UserBuilder userBuilder = null;
		
		if (user != null) {
			userBuilder = User.withUsername(username);
			userBuilder.disabled(!user.isEnabled());
			userBuilder.password(user.getPassword());
			String[] authorities = user.getRoles().stream()
					.map(RolesEntity::getRoleName)
					.toArray(String[]::new);
			userBuilder.authorities(authorities);
		}
		else {
			throw new UsernameNotFoundException(username);
		}
		
		return userBuilder.build();
	}
}
