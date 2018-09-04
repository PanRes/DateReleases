package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.SeriesEntity;

import java.util.List;

public interface SeriesDao {
	
	List<SeriesEntity> getAllSeries();
	SeriesEntity getSeriesById(int id);
	SeriesEntity getSeriesByName(String name);
	String getSeriesNameBySeriesId(int id);
}
