package gr.pr.date_releases.dao;

import gr.pr.date_releases.entity.SeriesEntity;
import gr.pr.date_releases.entity.SeriesEpisodesEntity;

import java.util.Set;

public interface SeriesEpisodesDao {
	
	Set<SeriesEpisodesEntity> getAllSeriesEpisodes();
	Set<SeriesEpisodesEntity> getSeriesEpisodesBySeries(SeriesEntity series);
	Set<SeriesEpisodesEntity> getSeriesEpisodesUserFavorites(Set<SeriesEntity> series);
	SeriesEpisodesEntity getSeriesEpisodeById(int id);
	int getCountOfEpisodesBySeries(SeriesEntity series);
	String updateSeriesEpisodesNotes(int id, String addNotes);
	
}
