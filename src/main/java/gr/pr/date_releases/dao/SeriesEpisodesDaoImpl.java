package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.SeriesEpisodesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Repository
public class SeriesEpisodesDaoImpl implements SeriesEpisodesDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Set<SeriesEpisodesEntity> getAllSeriesEpisodes() {
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<SeriesEpisodesEntity> query = session.createNamedQuery("SeriesEpisodes.findAll");
		
		return new HashSet<>(query.getResultList());
	}
	
	@Override
	public Set<SeriesEpisodesEntity> getSeriesEpisodesBySeries(SeriesEntity series) {
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<SeriesEpisodesEntity> query = session
				.createNamedQuery("SeriesEpisodes.findEpisodesBySeries", SeriesEpisodesEntity.class)
				.setParameter("series",series);
		
		return new HashSet<>(query.getResultList());
	}

	@Override
	public Set<SeriesEpisodesEntity> getSeriesEpisodesUserFavorites(Set<SeriesEntity> series) {
		
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<SeriesEpisodesEntity> query = session
				.createNamedQuery("SeriesEpisode.findSeriesEpisodesInSeries", SeriesEpisodesEntity.class)
				.setParameter("series",series);
		
		return new HashSet<>(query.getResultList());
	}

	@Override
	public SeriesEpisodesEntity getSeriesEpisodeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(SeriesEpisodesEntity.class, id);
	}
	
	@Override
	public int getCountOfEpisodesBySeries(SeriesEntity series) {
		return this.getSeriesEpisodesBySeries(series).size();
	}
	
	@Override
	public String updateSeriesEpisodesNotes(int id, String addNotes) {
		Session session = sessionFactory.getCurrentSession();
		
		SeriesEpisodesEntity seriesEpisode = session.get(SeriesEpisodesEntity.class,id);
		
		String newNotes = seriesEpisode.getNotes().concat(", " + addNotes);
		
		seriesEpisode.setNotes(newNotes);
		
		return newNotes;
	}
}
