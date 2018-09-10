package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<UserEntity> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<UserEntity> query = session.createNamedQuery("User.findAll", UserEntity.class);
		
		return query.getResultList();
	}
	
	@Override
	public UserEntity getUserByUserName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<UserEntity> query = session
				.createNamedQuery("User.findUserByUserName", UserEntity.class)
				.setParameter("userName", userName);
		
		UserEntity user = new UserEntity();
		try {
			user = query.getResultList().get(0);
		}
		catch (Exception e) {
			System.out.println("User with user name: " + userName + ", was not found");
		}
		
		return user;
		
	}
	
}
