package gr.pr.date_releases.service;

import gr.pr.date_releases.dao.GenericDao;
import gr.pr.date_releases.dao.SeriesDao;
import gr.pr.date_releases.dao.SeriesEpisodesDao;
import gr.pr.date_releases.dao.UserDao;
import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.SeriesEpisodesEntity;
import gr.pr.date_releases.entity.UserEntity;
import gr.pr.date_releases.utils.GenericUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
@PropertySource("classpath:fileUrls.properties")
public class SeriesServiceImpl implements SeriesService {
	
	@Autowired
	private SeriesDao seriesDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SeriesEpisodesDao seriesEpisodesDao;
	
	@Autowired
	private GenericDao genericDao;
	
	@Autowired
	private Environment environment;
	
	@Override
	@Transactional
	public List<SeriesEntity> getAllSeries() {
		return seriesDao.getAllSeries();
	}
	
	@Override
	@Transactional
	public SeriesEntity getSeriesBySeriesName(String name) {
		return seriesDao.getSeriesByName(name);
	}
	
	@Override
	@Transactional
	public void saveOrUpdateSeries(SeriesEntity series) {
		genericDao.saveOrUpdate(series);
	}
	
	@Override
	public String uploadImgUrl(MultipartFile multipartFile, String seriesName) {
		
		try {
			GenericUtils.uploadFile(multipartFile, environment.getProperty("series.img.url"), seriesName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	@Override
	@Transactional
	public void addSeriesToUserFavorites(int seriesId) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		UserEntity user = userDao.getUserByUserNameOrEmail(userName);
		
		SeriesEntity series = seriesDao.getSeriesById(seriesId);
		
		series.addUser(user);
	}
	
	@Override
	@Transactional
	public void removeSeriesToUserFavorites(int seriesId) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		UserEntity user = userDao.getUserByUserNameOrEmail(userName);
		
		SeriesEntity series = seriesDao.getSeriesById(seriesId);
		
		series.removeUserFavorite(user);
	}
	
	@Override
	@Transactional
	public Set<SeriesEpisodesEntity> getSeriesEpisodes(String seriesName) {
		if (seriesName.equals("allSeries")) {
			return seriesEpisodesDao.getAllSeriesEpisodes();
		}
		else if (seriesName.equals("favorites")) {
			String userName = SecurityContextHolder.getContext().getAuthentication().getName();

			UserEntity user = userDao.getUserByUserNameOrEmail(userName);

			Set<SeriesEntity> series = user.getFavoriteSeries();

			return seriesEpisodesDao.getSeriesEpisodesUserFavorites(series);
		}
		else {
			return seriesEpisodesDao.getSeriesEpisodesBySeries(getSeriesBySeriesName(seriesName));
		}
	}
}
