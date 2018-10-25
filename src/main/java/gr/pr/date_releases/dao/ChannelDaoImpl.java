package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.SeriesTVChannel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ChannelDaoImpl implements ChannelDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<SeriesTVChannel> getAllChannels() {
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<SeriesTVChannel> query = session.createNamedQuery("SeriesTvChannel.findAll", SeriesTVChannel.class);
		
		return query.getResultList();
	}
	
	@Override
	public SeriesTVChannel getChannelById(int channelId) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(SeriesTVChannel.class, channelId);
	}
}
