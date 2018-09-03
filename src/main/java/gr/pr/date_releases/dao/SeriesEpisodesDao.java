package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.SeriesEntity;

import java.util.List;

public interface SeriesEpisodesDao {
	
	List<SeriesEpisodesDao> getSeriesEpisodesBySeries(SeriesEntity series);
	int getCountofEpisodesBySeries(SeriesEntity series);
	String updateSeriesEpisodesNotes(int id, String newNotes);
	
}
