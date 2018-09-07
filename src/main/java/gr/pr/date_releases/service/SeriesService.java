package gr.pr.date_releases.service;

import gr.pr.date_releases.entity.SeriesEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

public interface SeriesService {
	
	List<SeriesEntity> getAllSeries();
	SeriesEntity getSeriesBySeriesName(String name);
	void saveOrUpdateSeries(SeriesEntity series);
	String uploadImgUrl(MultipartFile multipartFile, String seriesName);
}
