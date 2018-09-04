package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.SeriesEpisodesEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import java.util.List;

public class SeriesEpisodesDaoImpl implements SeriesEpisodesDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<SeriesEpisodesEntity> getSeriesEpisodesBySeries(SeriesEntity series) {
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<SeriesEpisodesEntity> query = session
				.createNamedQuery("SeriesEpisodes.findEpisodesBySeries", SeriesEpisodesEntity.class)
				.setParameter("series",series);
		
		return query.getResultList();
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
