package gr.pr.date_releases.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class GenericDaoImpl implements GenericDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Object entity) {
		Session session = sessionFactory.getCurrentSession();
		
		session.save(entity);
	}

	@Override
	public void update(Object entity) {
		Session session = sessionFactory.getCurrentSession();

		session.update(entity);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(entity);
	}
	
	@Override
	public void delete(Object entity) {
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(entity);
	}
	
	@Override
	public void insertMultipleLines(List<Object> entities) {
		Session session = sessionFactory.getCurrentSession();
		
		entities.stream()
				.forEach(entity -> session.saveOrUpdate(entity));
	}
}
