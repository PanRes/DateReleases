package gr.pr.date_releases.service;

import gr.pr.date_releases.dao.GenericDao;
import gr.pr.date_releases.dao.SeriesDao;
import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.utils.GenericUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@PropertySource("classpath:fileUrls.properties")
public class SeriesService {

	@Autowired
	private SeriesDao seriesDao;
	
	@Autowired
	private GenericDao genericDao;
	
	@Autowired
	private Environment environment;
	
	@Transactional
	public List<SeriesEntity> getAllSeries() {
		return seriesDao.getAllSeries();
	}
	
	@Transactional
	public SeriesEntity getSeriesBySeriesName(String name) {
		return seriesDao.getSeriesByName(name);
	}
	
	@Transactional
	public void saveOrUpdateSeries(SeriesEntity series) {
		genericDao.saveOrUpdate(series);
	}
	
	public String uploadImgUrl(MultipartFile multipartFile, String seriesName) {
		
		try {
			GenericUtils.uploadFile(multipartFile, environment.getProperty("series.img.url"), seriesName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
}
