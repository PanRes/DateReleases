package gr.pr.date_releases.service;

import gr.pr.date_releases.dao.GenericDao;
import gr.pr.date_releases.dao.SeriesDao;
import gr.pr.date_releases.dao.UserDao;
import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserSeriesServiceImpl {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SeriesDao seriesDao;
	
	@Autowired
	private GenericDao genericDao;
	
	@Transactional
	public void removeUserFavoriteByUserName(SeriesEntity series, String userName) {
		UserEntity user = userDao.getUserByUserName(userName);
		
		series.removeUserFavorite(user);
		
		genericDao.saveOrUpdate(series);
	}
}
