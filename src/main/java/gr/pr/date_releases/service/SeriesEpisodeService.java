package gr.pr.date_releases.service;

import gr.pr.date_releases.entity.SeriesEpisodesEntity;

public interface SeriesEpisodeService {
	
	SeriesEpisodesEntity getSeriesEpisodeById(int id);
	void deleteSeriesEpisodeDate(int seriesEpisodeEntityId);
	void saveOrUpdateSeriesEpisode(SeriesEpisodesEntity seriesEpisodes);

}
