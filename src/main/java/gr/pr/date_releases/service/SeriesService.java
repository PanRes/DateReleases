package gr.pr.date_releases.service;

import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.SeriesEpisodesEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface SeriesService {
	
	List<SeriesEntity> getAllSeries();
	SeriesEntity getSeriesBySeriesName(String name);
	void saveOrUpdateSeries(SeriesEntity series);
	String uploadImgUrl(MultipartFile multipartFile, String seriesName);
	void addSeriesToUserFavorites(int seriesId);
	void removeSeriesToUserFavorites(int seriesId);
	Set<SeriesEpisodesEntity> getSeriesEpisodes(String seriesName);
}
