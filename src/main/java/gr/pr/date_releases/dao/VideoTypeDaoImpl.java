package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.VideoType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class VideoTypeDaoImpl implements VideoTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<VideoType> getAllVideoTypes() {
		Session session = sessionFactory.getCurrentSession();
		
		TypedQuery<VideoType> query = session.createNamedQuery("VideoType.findAll",VideoType.class);
		
		List<VideoType> videoTypes = query.getResultList();
		
//		Collections.sort(videoTypes);
		return videoTypes;
	}
	
	@Override
	public VideoType getVideoTypeById(int videoTypeId) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(VideoType.class,videoTypeId);
	}
	
}
