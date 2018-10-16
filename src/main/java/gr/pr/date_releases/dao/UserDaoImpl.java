package gr.pr.date_releases.dao;

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
	public UserEntity getUserByUserNameOrEmail(String userName) {
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<UserEntity> query = session
				.createNamedQuery("User.findUserByUserNameOrEmail", UserEntity.class)
				.setParameter("userName", userName);
		
		UserEntity user = new UserEntity();
		try {
			user = query.getResultList().get(0);
		}
		catch (Exception e) {
			System.out.println("User with user name/email: " + userName + ", was not found");
		}
		
		return user;
		
	}
	
	@Override
	public UserEntity getUserByUserNameOtherThan(String userName, int userId) {
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<UserEntity> query = session
				.createNamedQuery("User.findUserByUserNameOtherThan", UserEntity.class)
				.setParameter("userName", userName)
				.setParameter("id", userId);
		
		UserEntity user;
		try {
			user = query.getResultList().get(0);
			return user;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public UserEntity getUserByEmailOtherThan(String email, int userId) {
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<UserEntity> query = session
				.createNamedQuery("User.findUserByEmailOtherThan", UserEntity.class)
				.setParameter("email", email)
				.setParameter("id", userId);
		
		UserEntity user;
		try {
			user = query.getResultList().get(0);
			return user;
		}
		catch (Exception e) {
			return null;
		}
	}
}
