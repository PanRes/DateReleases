package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.SeriesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SeriesDaoImpl implements SeriesDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<SeriesEntity> getAllSeries() {
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<SeriesEntity> query = session.createNamedQuery("Series.findAll", SeriesEntity.class);
		
		return query.getResultList();
	}
	
	@Override
	public SeriesEntity getSeriesById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(SeriesEntity.class,id);
	}
	
	@Override
	public SeriesEntity getSeriesByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<SeriesEntity> query = session
				.createNamedQuery("Series.findSeriesByName",SeriesEntity.class)
				.setParameter("name", name);

		//TODO : fix in case no series was found
		return query.getResultList().get(0);
	}
	
	@Override
	public String getSeriesNameBySeriesId(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(SeriesEntity.class,id).getName();
	}
}
