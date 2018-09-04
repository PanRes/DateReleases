package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.SeriesEpisodesEntity;

import java.util.List;

public interface SeriesEpisodesDao {
	
	List<SeriesEpisodesEntity> getSeriesEpisodesBySeries(SeriesEntity series);
	int getCountOfEpisodesBySeries(SeriesEntity series);
	String updateSeriesEpisodesNotes(int id, String addNotes);
	
}
