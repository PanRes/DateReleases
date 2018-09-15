package gr.pr.date_releases.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenericDaoImpl implements GenericDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void upsert(Object entity) {
		Session session = sessionFactory.getCurrentSession();
		
		//FIXME : fill correct exception when entry does not exists
		try {
			session.update(entity);
		}
		catch (Exception e) {
			session.save(entity);
		}
	}
	
	@Override
	public void save(Object entity) {
		Session session = sessionFactory.getCurrentSession();
		
		session.save(entity);
	}
	
	@Override
	public void saveOrUpdate(Object entity) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(entity);
	}
	
	@Override
	public void deleteRow(Object entity) {
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
